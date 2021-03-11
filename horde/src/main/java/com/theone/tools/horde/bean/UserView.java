package com.theone.tools.horde.bean;

import com.theone.tools.sso.client.UserGroup;
import com.theone.tools.sso.client.UserLevel;
import com.theone.tools.sso.client.UserStatus;

/**
 * @author chenxiaotong
 */
public class UserView {

    private String username;

    private String phone;

    private UserGroup group;

    private String groupView;

    private UserLevel level;

    private String levelView;

    private String realName;

    private String email;

    private UserStatus status;

    private String statusView;

    private String createTime;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGroupView() {
        return groupView;
    }

    public void setGroupView(String groupView) {
        this.groupView = groupView;
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

    public UserGroup getGroup() {
        return group;
    }

    public void setGroup(UserGroup group) {
        this.group = group;
    }

    public UserLevel getLevel() {
        return level;
    }

    public void setLevel(UserLevel level) {
        this.level = level;
    }

    public String getLevelView() {
        return levelView;
    }

    public void setLevelView(String levelView) {
        this.levelView = levelView;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getStatusView() {
        return statusView;
    }

    public void setStatusView(String statusView) {
        this.statusView = statusView;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
