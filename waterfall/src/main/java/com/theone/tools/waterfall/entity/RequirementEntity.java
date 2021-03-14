package com.theone.tools.waterfall.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

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