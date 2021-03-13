package com.theone.tools.waterfall.vo;

import com.theone.tools.waterfall.model.user.Role;

public class UserInfoResp {

    private String username;

    private Role userRole;

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
