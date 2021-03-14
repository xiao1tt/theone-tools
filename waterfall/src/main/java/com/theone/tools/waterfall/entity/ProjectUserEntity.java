package com.theone.tools.waterfall.entity;

import com.theone.tools.waterfall.model.user.Role;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * (ProjectUser)实体类
 *
 * @author makejava
 * @since 2021-03-14 12:38:19
 */
public class ProjectUserEntity implements Serializable {
    private static final long serialVersionUID = -72600845844837923L;

    private Integer id;

    private Integer projectId;

    private String username;

    private Role userRole;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
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