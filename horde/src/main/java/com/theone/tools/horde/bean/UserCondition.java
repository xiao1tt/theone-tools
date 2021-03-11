package com.theone.tools.horde.bean;

import com.theone.tools.sso.client.UserGroup;
import com.theone.tools.sso.client.UserStatus;

public class UserCondition {
    private String username;
    private String realName;
    private String phone;
    private UserGroup group;
    private UserStatus status;

    public UserCondition() {
    }

    public UserCondition(String username, String realName, String phone, UserGroup group, UserStatus status) {
        this.username = username;
        this.realName = realName;
        this.phone = phone;
        this.group = group;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserGroup getGroup() {
        return group;
    }

    public void setGroup(UserGroup group) {
        this.group = group;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
