package com.theone.tools.waterfall.entity;

import com.theone.tools.waterfall.model.assignment.AssignmentStatus;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * (Assignment)实体类
 *
 * @author makejava
 * @since 2021-03-14 20:32:12
 */
public class AssignmentEntity implements Serializable {
    private static final long serialVersionUID = -49193335213165076L;
    
    private Integer id;
    
    private Integer projectId;
    
    private Integer requirementId;
    
    private Integer stageId;
    
    private String name;
    
    private String assignmentDesc;
    
    private AssignmentStatus assignmentStatus;
    
    private LocalDateTime updateTime;
    
    private LocalDateTime createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(Integer requirementId) {
        this.requirementId = requirementId;
    }

    public Integer getStageId() {
        return stageId;
    }

    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAssignmentDesc() {
        return assignmentDesc;
    }

    public void setAssignmentDesc(String assignmentDesc) {
        this.assignmentDesc = assignmentDesc;
    }

    public AssignmentStatus getAssignmentStatus() {
        return assignmentStatus;
    }

    public void setAssignmentStatus(AssignmentStatus assignmentStatus) {
        this.assignmentStatus = assignmentStatus;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

}