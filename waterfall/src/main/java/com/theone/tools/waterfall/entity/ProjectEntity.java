package com.theone.tools.waterfall.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * (Project)实体类
 *
 * @author makejava
 * @since 2021-03-11 19:52:25
 */
public class ProjectEntity implements Serializable {
    private static final long serialVersionUID = 844618018105490478L;
    
    private Integer id;
    
    private Integer groupId;
    
    private String name;
    
    private String projectDesc;
    
    private LocalDateTime updateTime;
    
    private LocalDateTime createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
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