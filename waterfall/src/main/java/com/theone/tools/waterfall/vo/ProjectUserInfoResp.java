package com.theone.tools.waterfall.vo;

import com.theone.tools.waterfall.model.user.Role;

/**
 * @author chenxiaotong
 */
public class ProjectUserInfoResp {

    private Integer projectId;

    private String username;

    private Role role;

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
