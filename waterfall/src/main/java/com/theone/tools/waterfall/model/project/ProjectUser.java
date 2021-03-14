package com.theone.tools.waterfall.model.project;

import com.theone.tools.waterfall.model.user.Role;

public class ProjectUser {

    private Integer projectId;

    private String username;

    private Role userRole;

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
}
