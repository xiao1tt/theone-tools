package com.theone.tools.horde.bean;

import com.theone.tools.sso.client.UserGroup;
import com.theone.tools.sso.client.UserLevel;
import com.theone.tools.sso.client.UserStatus;

public class UserUpdateReq {
    private String phone;

    private UserGroup group;

    private UserLevel level;

    private String realName;

    private String email;

    private UserStatus status;

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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
