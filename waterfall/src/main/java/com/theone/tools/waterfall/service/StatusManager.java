package com.theone.tools.waterfall.service;

import com.alibaba.fastjson.JSON;
import com.theone.tools.waterfall.dao.AssignmentDao;
import com.theone.tools.waterfall.dao.AssignmentWorkerDao;
import com.theone.tools.waterfall.dao.RequirementDao;
import com.theone.tools.waterfall.dao.RequirementStageDao;
import com.theone.tools.waterfall.entity.AssignmentEntity;
import com.theone.tools.waterfall.entity.AssignmentWorkerEntity;
import com.theone.tools.waterfall.entity.RequirementEntity;
import com.theone.tools.waterfall.entity.RequirementStageEntity;
import com.theone.tools.waterfall.model.assignment.Assignment;
import com.theone.tools.waterfall.model.assignment.AssignmentStatus;
import com.theone.tools.waterfall.model.requirement.RequirementStatus;
import com.theone.tools.waterfall.model.requirement.StageStatus;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class StatusManager {

    @Resource
    private AssignmentWorkerDao assignmentWorkerDao;
    @Resource
    private AssignmentDao assignmentDao;
    @Resource
    private RequirementStageDao requirementStageDao;
    @Resource
    private RequirementDao requirementDao;
    @Resource
    private AssignmentService assignmentService;
    @Resource
    private RequirementStageService stageService;

    public boolean updateWorkerStatus(Integer assignmentId, String username, AssignmentStatus after) {
        return this.updateWorkerStatus(assignmentId, username, null, after);
    }

    public boolean updateWorkerStatus(Integer assignmentId, String username,
            AssignmentStatus before, AssignmentStatus after) {
        int i = assignmentWorkerDao.updateStatus(assignmentId, username, before, after);
        if (i > 0) {
            afterWorkerStatusUpdate(assignmentId, username, before, after);
            return true;
        }
        return false;
    }

    private void afterWorkerStatusUpdate(Integer assignmentId, String username, AssignmentStatus before,
            AssignmentStatus after) {
        if (after == AssignmentStatus.DOING) {
            this.updateAssignmentStatus(assignmentId, AssignmentStatus.WAITING, AssignmentStatus.DOING);
        }

        if (after == AssignmentStatus.DONE) {
            AssignmentWorkerEntity query = new AssignmentWorkerEntity();
            query.setAssignmentId(assignmentId);

            List<AssignmentWorkerEntity> workerEntities = assignmentWorkerDao.queryAll(query);
            if (workerEntities.stream().allMatch(item -> item.getWorkStatus() == AssignmentStatus.DONE)) {
                this.updateAssignmentStatus(assignmentId, AssignmentStatus.DONE);
            }
        }
    }

    public boolean updateAssignmentStatus(Integer assignmentId, AssignmentStatus after) {
        return this.updateAssignmentStatus(assignmentId, null, after);
    }

    public boolean updateAssignmentStatus(Integer assignmentId, AssignmentStatus before, AssignmentStatus after) {
        int i = assignmentDao.updateStatus(assignmentId, before, after);
        if (i > 0) {
            afterAssignmentStatusUpdate(assignmentId, before, after);
            return true;
        }

        return false;
    }

    private void afterAssignmentStatusUpdate(Integer assignmentId, AssignmentStatus before, AssignmentStatus after) {
        AssignmentEntity assignmentEntity = assignmentDao.queryById(assignmentId);

        if (after == AssignmentStatus.DOING) {
            this.updateStageStatus(assignmentEntity.getStageId(), StageStatus.DOING);
            RequirementEntity requirementEntity = requirementDao.queryById(assignmentEntity.getRequirementId());
            requirementEntity.setCurrentAssignment(JSON.toJSONString(assignmentService.info(assignmentEntity.getId())));
            requirementDao.update(requirementEntity);
        }

        if (after == AssignmentStatus.DONE) {
            AssignmentEntity query = new AssignmentEntity();
            query.setStageId(assignmentEntity.getStageId());

            List<AssignmentEntity> assignmentEntities = assignmentDao.queryAll(query);
            if (assignmentEntities.stream().allMatch(item -> item.getAssignmentStatus() == AssignmentStatus.DONE)) {
                this.updateStageStatus(assignmentEntity.getStageId(), StageStatus.DONE);
            }
        }
    }

    public boolean updateStageStatus(Integer stageId, StageStatus after) {
        return this.updateStageStatus(stageId, null, after);
    }

    public boolean updateStageStatus(Integer stageId, StageStatus before, StageStatus after) {
        int i = requirementStageDao.updateStatus(stageId, before, after);
        if (i > 0) {
            afterStageStatusUpdate(stageId, before, after);
            return true;
        }
        return false;
    }

    private void afterStageStatusUpdate(Integer stageId, StageStatus before, StageStatus after) {
        RequirementStageEntity stageEntity = requirementStageDao.queryById(stageId);

        if (after == StageStatus.WAITING) {
            RequirementEntity requirementEntity = requirementDao.queryById(stageEntity.getRequirementId());
            requirementEntity.setCurrentStage(JSON.toJSONString(stageService.info(stageEntity.getId())));
            requirementDao.update(requirementEntity);
        }

        if (after == StageStatus.DOING) {
            this.updateRequirementStatus(stageEntity.getRequirementId(), RequirementStatus.DOING);
        }

        if (after == StageStatus.DONE) {
            RequirementStageEntity query = new RequirementStageEntity();
            query.setRequirementId(stageEntity.getRequirementId());
            List<RequirementStageEntity> stageEntities = requirementStageDao.queryAll(query);
            Map<Integer, RequirementStageEntity> orderMap = stageEntities.stream()
                    .collect(Collectors.toMap(RequirementStageEntity::getStageOrder, Function.identity()));
            RequirementStageEntity next = orderMap.get(stageEntity.getStageOrder() + 1);

            if (next == null) {
                this.updateRequirementStatus(stageEntity.getRequirementId(), RequirementStatus.CLOSE);
            } else {
                this.updateStageStatus(next.getId(), StageStatus.NOT_ACTIVE, StageStatus.WAITING);
            }
        }

        if (after == StageStatus.REJECT) {
            Assignment assignment = new Assignment();
            assignment.setProjectId(stageEntity.getProjectId());
            assignment.setRequirementId(stageEntity.getRequirementId());
            assignment.setStageId(stageEntity.getId());
            assignment.setStageType(stageEntity.getType());
            assignment.setName("驳回后续任务");
            assignment.setAssignmentDesc("默认驳回后续任务");
            assignment.setAssignmentStatus(AssignmentStatus.WAIT_ALLOCATE);
            assignmentService.add(assignment);
        }
    }

    public boolean updateRequirementStatus(Integer requirementId, RequirementStatus after) {
        return this.updateRequirementStatus(requirementId, null, after);
    }

    public boolean updateRequirementStatus(Integer requirementId, RequirementStatus before, RequirementStatus after) {
        int i = requirementDao.updateStatus(requirementId, before, after);
        if (i > 0) {
            afterRequirementStatusUpdate(requirementId, before, after);
            return true;
        }
        return false;
    }


    private void afterRequirementStatusUpdate(Integer requirementId, RequirementStatus before,
            RequirementStatus after) {

    }
}
