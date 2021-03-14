package com.theone.tools.waterfall.service;

import com.theone.tools.waterfall.dao.AssignmentDao;
import com.theone.tools.waterfall.entity.AssignmentEntity;
import com.theone.tools.waterfall.model.assignment.Assignment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AssignmentService {
    @Resource
    private AssignmentDao assignmentDao;

    public void add(Assignment assignment) {
        assignmentDao.insert(adapt(assignment));
    }

    private AssignmentEntity adapt(Assignment assignment) {
        if (assignment == null) {
            return null;
        }

        AssignmentEntity entity = new AssignmentEntity();
        entity.setProjectId(assignment.getProjectId());
        entity.setRequirementId(assignment.getRequirementId());
        entity.setStageId(assignment.getStageId());
        entity.setName(assignment.getName());
        entity.setAssignmentDesc(assignment.getAssignmentDesc());
        entity.setAssignmentStatus(assignment.getAssignmentStatus());

        return entity;
    }
}
