package com.theone.tools.waterfall.vo;

import com.theone.tools.waterfall.model.user.Role;

public class ProjectUserAddReq {

    private String username;

    private Role role;

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
