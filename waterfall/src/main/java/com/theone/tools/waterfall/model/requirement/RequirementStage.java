package com.theone.tools.waterfall.model.requirement;

import com.theone.tools.sso.client.UserGroup;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * (RequirementStage)实体类
 *
 * @author makejava
 * @since 2021-03-14 19:32:37
 */
public class RequirementStage implements Serializable {
    private static final long serialVersionUID = -66558812526794796L;

    private Integer id;

    private Integer projectId;

    private Integer requirementId;

    private Integer templateId;

    private Integer templateStageId;

    private String name;

    private String stageDesc;

    private Integer stageOrder;

    private UserGroup requiredGroup;

    private String director;

    private String template;

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

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public Integer getTemplateStageId() {
        return templateStageId;
    }

    public void setTemplateStageId(Integer templateStageId) {
        this.templateStageId = templateStageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStageDesc() {
        return stageDesc;
    }

    public void setStageDesc(String stageDesc) {
        this.stageDesc = stageDesc;
    }

    public Integer getStageOrder() {
        return stageOrder;
    }

    public void setStageOrder(Integer stageOrder) {
        this.stageOrder = stageOrder;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public UserGroup getRequiredGroup() {
        return requiredGroup;
    }

    public void setRequiredGroup(UserGroup requiredGroup) {
        this.requiredGroup = requiredGroup;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
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