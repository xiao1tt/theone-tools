package com.theone.tools.waterfall.service;

import com.theone.tools.waterfall.dao.AssignmentDao;
import com.theone.tools.waterfall.dao.AssignmentWorkerDao;
import com.theone.tools.waterfall.entity.AssignmentEntity;
import com.theone.tools.waterfall.entity.AssignmentWorkerEntity;
import com.theone.tools.waterfall.entity.RequirementStageEntity;
import com.theone.tools.waterfall.model.assignment.Assignment;
import com.theone.tools.waterfall.model.assignment.AssignmentStatus;
import com.theone.tools.waterfall.model.assignment.AssignmentStruct;
import com.theone.tools.waterfall.model.assignment.AssignmentWorker;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class AssignmentService {

    @Resource
    private AssignmentDao assignmentDao;
    @Resource
    private AssignmentWorkerDao assignmentWorkerDao;
    @Resource
    private StatusManager statusManager;

    public void add(Assignment assignment) {
        assignmentDao.insert(adapt(assignment));
    }

    public void init(RequirementStageEntity stageEntity) {

        Assignment assignment = new Assignment();
        assignment.setProjectId(stageEntity.getProjectId());
        assignment.setRequirementId(stageEntity.getRequirementId());
        assignment.setStageId(stageEntity.getId());
        assignment.setStageType(stageEntity.getType());
        assignment.setName(stageEntity.getName());
        assignment.setAssignmentDesc("默认任务");
        assignment.setAssignmentStatus(AssignmentStatus.WAIT_ALLOCATE);
        assignmentDao.insert(adapt(assignment));
    }

    private AssignmentEntity adapt(Assignment assignment) {
        if (assignment == null) {
            return null;
        }

        AssignmentEntity entity = new AssignmentEntity();
        entity.setId(assignment.getId());
        entity.setProjectId(assignment.getProjectId());
        entity.setRequirementId(assignment.getRequirementId());
        entity.setStageId(assignment.getStageId());
        entity.setStageType(assignment.getStageType());
        entity.setName(assignment.getName());
        entity.setAssignmentDesc(assignment.getAssignmentDesc());
        entity.setAssignmentStatus(assignment.getAssignmentStatus());
        entity.setExpectDate(assignment.getExpectDate());
        return entity;
    }

    private Assignment adapt(AssignmentEntity entity) {
        if (entity == null) {
            return null;
        }

        Assignment assignment = new Assignment();

        assignment.setId(entity.getId());
        assignment.setProjectId(entity.getProjectId());
        assignment.setRequirementId(entity.getRequirementId());
        assignment.setStageId(entity.getStageId());
        assignment.setStageType(entity.getStageType());
        assignment.setName(entity.getName());
        assignment.setAssignmentDesc(entity.getAssignmentDesc());
        assignment.setAssignmentStatus(entity.getAssignmentStatus());
        assignment.setExpectDate(entity.getExpectDate());
        assignment.setUpdateTime(entity.getUpdateTime());
        assignment.setCreateTime(entity.getCreateTime());

        return assignment;
    }

    public List<Assignment> listByUser(String username) {
        return this.listByProject(null, username);
    }

    public List<Assignment> listByProject(Integer projectId) {
        return this.listByProject(projectId, null);
    }

    public List<Assignment> listByProject(Integer projectId, String username) {
        AssignmentEntity query = new AssignmentEntity();
        query.setProjectId(projectId);

        List<AssignmentEntity> assignmentEntities = assignmentDao.queryAll(query);

        if (StringUtils.isNotEmpty(username)) {
            AssignmentWorkerEntity workerQuery = new AssignmentWorkerEntity();
            workerQuery.setWorker(username);

            List<AssignmentWorkerEntity> workerEntities = assignmentWorkerDao.queryAll(workerQuery);

            List<Integer> assignmentIds = workerEntities.stream().map(AssignmentWorkerEntity::getAssignmentId)
                    .collect(Collectors.toList());
            assignmentEntities.removeIf(entity -> !assignmentIds.contains(entity.getId()));
        }

        return assignmentEntities.stream().map(this::adapt).collect(Collectors.toList());
    }

    private List<Assignment> queryByIds(List<Integer> ids) {
        return assignmentDao.queryByIds(ids).stream().map(this::adapt).collect(Collectors.toList());
    }

    public List<String> simpleWorkers(Integer assignmentId) {
        AssignmentWorkerEntity query = new AssignmentWorkerEntity();
        query.setAssignmentId(assignmentId);

        return assignmentWorkerDao.queryAll(query).stream().map(AssignmentWorkerEntity::getWorker)
                .collect(Collectors.toList());
    }

    public List<AssignmentWorker> workers(Integer assignmentId) {
        AssignmentWorkerEntity query = new AssignmentWorkerEntity();
        query.setAssignmentId(assignmentId);

        return assignmentWorkerDao.queryAll(query).stream().map(this::adapt).collect(Collectors.toList());
    }

    public void addWorker(Integer assignmentId, List<String> users) {
        Assignment assignment = this.info(assignmentId);

        List<String> current = this.simpleWorkers(assignmentId);

        users.removeAll(current);

        List<AssignmentWorkerEntity> workerList = users.stream().map(username -> {
            AssignmentWorkerEntity entity = new AssignmentWorkerEntity();
            entity.setAssignmentId(assignmentId);
            entity.setStageId(assignment.getStageId());
            entity.setRequirementId(assignment.getRequirementId());
            entity.setProjectId(assignment.getProjectId());
            entity.setWorker(username);
            entity.setWorkStatus(AssignmentStatus.WAITING);
            return entity;
        }).collect(Collectors.toList());

        assignmentWorkerDao.insertList(workerList);

        this.afterAddWorker(assignmentId, users);
    }

    private void afterAddWorker(Integer assignmentId, List<String> users) {
        statusManager.updateAssignmentStatus(assignmentId, AssignmentStatus.WAIT_ALLOCATE, AssignmentStatus.WAITING);
        notifyWorkers(users);
    }

    private void notifyWorkers(List<String> users) {
        // TODO: 2021/3/24
    }

    private Assignment info(Integer assignmentId) {
        return adapt(assignmentDao.queryById(assignmentId));
    }

    public List<AssignmentStruct> listStructByRequirement(Integer requirementId) {
        AssignmentEntity assignmentQuery = new AssignmentEntity();
        assignmentQuery.setRequirementId(requirementId);

        List<AssignmentEntity> assignmentEntities = assignmentDao.queryAll(assignmentQuery);

        AssignmentWorkerEntity workerQuery = new AssignmentWorkerEntity();
        workerQuery.setRequirementId(requirementId);

        List<AssignmentWorkerEntity> assignmentWorkerEntities = assignmentWorkerDao.queryAll(workerQuery);
        Map<Integer, List<AssignmentWorkerEntity>> assignmentWorkerMap = assignmentWorkerEntities.stream()
                .collect(Collectors.groupingBy(AssignmentWorkerEntity::getAssignmentId));

        List<AssignmentStruct> result = new ArrayList<>();

        for (AssignmentEntity assignmentEntity : assignmentEntities) {
            List<AssignmentWorkerEntity> workerEntities = assignmentWorkerMap.get(assignmentEntity.getId());
            AssignmentStruct struct = assembleStruct(assignmentEntity, workerEntities);
            result.add(struct);
        }

        return result;
    }

    public List<AssignmentStruct> listStructByStage(Integer stageId) {
        AssignmentEntity assignmentQuery = new AssignmentEntity();
        assignmentQuery.setStageId(stageId);

        List<AssignmentEntity> assignmentEntities = assignmentDao.queryAll(assignmentQuery);

        AssignmentWorkerEntity workerQuery = new AssignmentWorkerEntity();
        workerQuery.setStageId(stageId);

        List<AssignmentWorkerEntity> assignmentWorkerEntities = assignmentWorkerDao.queryAll(workerQuery);
        Map<Integer, List<AssignmentWorkerEntity>> assignmentWorkerMap = assignmentWorkerEntities.stream()
                .collect(Collectors.groupingBy(AssignmentWorkerEntity::getAssignmentId));

        List<AssignmentStruct> result = new ArrayList<>();

        for (AssignmentEntity assignmentEntity : assignmentEntities) {
            List<AssignmentWorkerEntity> workerEntities = assignmentWorkerMap.get(assignmentEntity.getId());
            AssignmentStruct struct = assembleStruct(assignmentEntity, workerEntities);
            result.add(struct);
        }

        return result;
    }

    private AssignmentStruct assembleStruct(AssignmentEntity assignmentEntity,
            List<AssignmentWorkerEntity> workerEntities) {
        AssignmentStruct struct = new AssignmentStruct();
        struct.setAssignment(adapt(assignmentEntity));
        struct.setWorkers(workerEntities != null
                ? workerEntities.stream().map(this::adapt).collect(Collectors.toList())
                : Lists.newArrayList());
        return struct;
    }

    public AssignmentStruct struct(Integer id) {
        AssignmentEntity assignmentEntity = assignmentDao.queryById(id);
        AssignmentWorkerEntity query = new AssignmentWorkerEntity();
        query.setAssignmentId(id);
        List<AssignmentWorkerEntity> workerEntities = assignmentWorkerDao.queryAll(query);
        return assembleStruct(assignmentEntity, workerEntities);
    }

    private AssignmentWorker adapt(AssignmentWorkerEntity entity) {
        if (entity == null) {
            return null;
        }

        AssignmentWorker worker = new AssignmentWorker();
        worker.setId(entity.getId());
        worker.setAssignmentId(entity.getAssignmentId());
        worker.setStageId(entity.getStageId());
        worker.setRequirementId(entity.getRequirementId());
        worker.setProjectId(entity.getProjectId());
        worker.setWorker(entity.getWorker());
        worker.setWorkStatus(entity.getWorkStatus());
        worker.setStartTime(entity.getStartTime());
        worker.setCompleteTime(entity.getCompleteTime());
        worker.setUpdateTime(entity.getUpdateTime());
        worker.setCreateTime(entity.getCreateTime());

        return worker;
    }

    public void delete(Integer id) {
        assignmentDao.deleteById(id);
        assignmentWorkerDao.deleteByAssignment(id);
    }

    public void updateInfo(Assignment assignment) {
        assignmentDao.update(adapt(assignment));
    }

    public void start(Integer assignmentId, String username) {
        AssignmentWorkerEntity entity = assignmentWorkerDao.query(assignmentId, username);
        if (entity.getWorkStatus() == AssignmentStatus.WAITING) {
            entity.setWorkStatus(AssignmentStatus.DOING);
            assignmentWorkerDao.update(entity);
        }

        this.afterWorkerStart(assignmentId, username);
    }

    private void afterWorkerStart(Integer assignmentId, String username) {
        statusManager.updateWorkerStatus(assignmentId, username, AssignmentStatus.WAITING, AssignmentStatus.DOING);
    }
}
