package com.theone.tools.waterfall.service;

import com.theone.tools.waterfall.dao.AssignmentDao;
import com.theone.tools.waterfall.dao.AssignmentWorkerDao;
import com.theone.tools.waterfall.entity.AssignmentEntity;
import com.theone.tools.waterfall.entity.AssignmentWorkerEntity;
import com.theone.tools.waterfall.model.assignment.Assignment;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignmentService {
    @Resource
    private AssignmentDao assignmentDao;
    @Resource
    private AssignmentWorkerDao assignmentWorkerDao;

    public void add(Assignment assignment) {
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
        assignment.setUpdateTime(entity.getUpdateTime());
        assignment.setCreateTime(entity.getCreateTime());

        return assignment;
    }

    public List<Assignment> list(String username) {
        return this.list(null, username);
    }

    public List<Assignment> list(Integer projectId) {
        return this.list(projectId, null);
    }

    public List<Assignment> list(Integer projectId, String username) {
        AssignmentEntity query = new AssignmentEntity();
        query.setProjectId(projectId);

        List<AssignmentEntity> assignmentEntities = assignmentDao.queryAll(query);

        if (StringUtils.isNotEmpty(username)) {
            AssignmentWorkerEntity workerQuery = new AssignmentWorkerEntity();
            workerQuery.setWorker(username);

            List<AssignmentWorkerEntity> workerEntities = assignmentWorkerDao.queryAll(workerQuery);

            List<Integer> assignmentIds = workerEntities.stream().map(AssignmentWorkerEntity::getAssignmentId).collect(Collectors.toList());
            assignmentEntities.removeIf(entity -> !assignmentIds.contains(entity.getId()));
        }

        return assignmentEntities.stream().map(this::adapt).collect(Collectors.toList());
    }

    private List<Assignment> queryByIds(List<Integer> ids) {
        return assignmentDao.queryByIds(ids).stream().map(this::adapt).collect(Collectors.toList());
    }

    public List<String> currentWorker(Integer assignmentId) {
        AssignmentWorkerEntity query = new AssignmentWorkerEntity();
        query.setAssignmentId(assignmentId);

        return assignmentWorkerDao.queryAll(query).stream().map(AssignmentWorkerEntity::getWorker).collect(Collectors.toList());
    }
}
