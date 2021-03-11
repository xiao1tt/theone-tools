package com.theone.tools.horde.bean;

import com.theone.tools.sso.client.UserGroup;

/**
 * @author chenxiaotong
 */
public class UserCompleteReq {
    private String username;

    private UserGroup group;

    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserGroup getGroup() {
        return group;
    }

    public void setGroup(UserGroup group) {
        this.group = group;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
