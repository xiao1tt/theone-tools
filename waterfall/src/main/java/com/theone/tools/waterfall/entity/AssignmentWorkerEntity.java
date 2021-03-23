package com.theone.tools.waterfall.entity;

import com.theone.tools.waterfall.model.assignment.AssignmentStatus;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * (AssignmentWorker)实体类
 *
 * @author makejava
 * @since 2021-03-17 13:55:49
 */
public class AssignmentWorkerEntity implements Serializable {
    private static final long serialVersionUID = 551094327404594046L;
    
    private Integer id;
    
    private Integer assignmentId;
    
    private Integer stageId;
    
    private Integer requirementId;
    
    private Integer projectId;
    
    private String worker;
    
    private AssignmentStatus workStatus;
    
    private LocalDateTime startTime;
    
    private LocalDateTime completeTime;
    
    private LocalDateTime updateTime;
    
    private LocalDateTime createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Integer assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Integer getStageId() {
        return stageId;
    }

    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }

    public Integer getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(Integer requirementId) {
        this.requirementId = requirementId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public AssignmentStatus getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(AssignmentStatus workStatus) {
        this.workStatus = workStatus;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(LocalDateTime completeTime) {
        this.completeTime = completeTime;
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