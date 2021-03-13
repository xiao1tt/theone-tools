package com.theone.tools.waterfall.model.user;

/**
 * @author chenxiaotong
 */
public class User {

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
