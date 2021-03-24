package com.theone.tools.waterfall.service;

import com.theone.tools.waterfall.dao.AssignmentDao;
import com.theone.tools.waterfall.dao.AssignmentWorkerDao;
import com.theone.tools.waterfall.dao.RequirementDao;
import com.theone.tools.waterfall.dao.RequirementStageDao;
import com.theone.tools.waterfall.entity.AssignmentEntity;
import com.theone.tools.waterfall.entity.AssignmentWorkerEntity;
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

    public void updateWorkerStatus(Integer assignmentId, String username, AssignmentStatus after) {
        assignmentWorkerDao.updateStatus(assignmentId, username, after);
        afterWorkerStatusUpdate(assignmentId, username, null, after);
    }

    public void updateWorkerStatus(Integer assignmentId, String username,
            AssignmentStatus before, AssignmentStatus after) {
        assignmentWorkerDao.compareAndUpdateStatus(assignmentId, username, before, after);
        afterWorkerStatusUpdate(assignmentId, username, before, after);
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

    public void updateAssignmentStatus(Integer assignmentId, AssignmentStatus before, AssignmentStatus after) {
        assignmentDao.compareAndUpdateStatus(assignmentId, before, after);
        afterAssignmentStatusUpdate(assignmentId, before, after);
    }

    public void updateAssignmentStatus(Integer assignmentId, AssignmentStatus after) {
        assignmentDao.updateStatus(assignmentId, after);
        afterAssignmentStatusUpdate(assignmentId, null, after);
    }

    private void afterAssignmentStatusUpdate(Integer assignmentId, AssignmentStatus before, AssignmentStatus after) {
        AssignmentEntity assignmentEntity = assignmentDao.queryById(assignmentId);

        if (after == AssignmentStatus.DOING) {
            this.updateStageStatus(assignmentEntity.getStageId(), StageStatus.DOING);
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

    public void updateStageStatus(Integer stageId, StageStatus before, StageStatus after) {
        requirementStageDao.compareAndUpdateStatus(stageId, before, after);
        afterStageStatusUpdate(stageId, before, after);
    }

    public void updateStageStatus(Integer stageId, StageStatus after) {
        requirementStageDao.updateStatus(stageId, after);
        afterStageStatusUpdate(stageId, null, after);
    }

    private void afterStageStatusUpdate(Integer stageId, StageStatus before, StageStatus after) {
        RequirementStageEntity stageEntity = requirementStageDao.queryById(stageId);

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

    public void updateRequirementStatus(Integer requirementId, RequirementStatus before, RequirementStatus after) {
        requirementDao.compareAndUpdateStatus(requirementId, before, after);
        afterRequirementStatusUpdate(requirementId, before, after);
    }

    public void updateRequirementStatus(Integer requirementId, RequirementStatus after) {
        requirementDao.updateStatus(requirementId, after);
        afterRequirementStatusUpdate(requirementId, null, after);
    }

    private void afterRequirementStatusUpdate(Integer requirementId, RequirementStatus before,
            RequirementStatus after) {

    }
}
