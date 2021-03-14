package com.theone.tools.waterfall.entity;

import com.theone.tools.sso.client.UserGroup;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * (RequirementTemplateStage)实体类
 *
 * @author makejava
 * @since 2021-03-14 17:43:12
 */
public class RequirementTemplateStageEntity implements Serializable {
    private static final long serialVersionUID = 227857692434404572L;
    
    private Integer id;
    
    private Integer templateId;
    
    private String name;
    
    private String stageDesc;
    
    private Integer stageOrder;
    
    private UserGroup requiredGroup;

    private String director;

    private LocalDateTime updateTime;
    
    private LocalDateTime createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public UserGroup getRequiredGroup() {
        return requiredGroup;
    }

    public void setRequiredGroup(UserGroup requiredGroup) {
        this.requiredGroup = requiredGroup;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
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