package com.theone.tools.waterfall.model.requirement;

import com.theone.tools.waterfall.model.assignment.Assignment;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Requirement {

    private Integer id;

    private Integer projectId;

    private Integer templateId;

    private String name;

    private String requirementDesc;

    private String requirementOwner;

    private LocalDate expectDate;

    private int priority;

    private RequirementStatus requirementStatus;

    private RequirementStage currentStage;

    private Assignment currentAssignment;

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

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequirementDesc() {
        return requirementDesc;
    }

    public void setRequirementDesc(String requirementDesc) {
        this.requirementDesc = requirementDesc;
    }

    public String getRequirementOwner() {
        return requirementOwner;
    }

    public void setRequirementOwner(String requirementOwner) {
        this.requirementOwner = requirementOwner;
    }

    public LocalDate getExpectDate() {
        return expectDate;
    }

    public void setExpectDate(LocalDate expectDate) {
        this.expectDate = expectDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public RequirementStatus getRequirementStatus() {
        return requirementStatus;
    }

    public void setRequirementStatus(RequirementStatus requirementStatus) {
        this.requirementStatus = requirementStatus;
    }

    public RequirementStage getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(RequirementStage currentStage) {
        this.currentStage = currentStage;
    }

    public Assignment getCurrentAssignment() {
        return currentAssignment;
    }

    public void setCurrentAssignment(Assignment currentAssignment) {
        this.currentAssignment = currentAssignment;
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
