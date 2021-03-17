package com.theone.tools.waterfall.entity;

import com.theone.tools.waterfall.model.requirement.RequirementStatus;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * (Requirement)实体类
 *
 * @author makejava
 * @since 2021-03-14 18:36:30
 */
public class RequirementEntity implements Serializable {
    private static final long serialVersionUID = -69957765904367413L;

    private Integer id;

    private Integer projectId;

    private Integer templateId;

    private String name;

    private String requirementDesc;

    private String requirementOwner;

    private LocalDate expectDate;

    private int priority;

    private RequirementStatus requirementStatus;

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

    public RequirementStatus getRequirementStatus() {
        return requirementStatus;
    }

    public void setRequirementStatus(RequirementStatus requirementStatus) {
        this.requirementStatus = requirementStatus;
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