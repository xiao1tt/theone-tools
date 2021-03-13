package com.theone.tools.sso.client;

public class IUserView {

    private String username;

    private String phone;

    private UserGroup group;

    private String groupView;

    private UserLevel level;

    private String levelView;

    private String realName;

    private String email;

    private String avatar;

    private UserStatus status;

    private String statusView;

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

    public String getGroupView() {
        return groupView;
    }

    public void setGroupView(String groupView) {
        this.groupView = groupView;
    }

    public String getLevelView() {
        return levelView;
    }

    public void setLevelView(String levelView) {
        this.levelView = levelView;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public static IUserView from(IUser user) {
        if (user == null) {
            return null;
        }

        IUserView view = new IUserView();
        view.setUsername(user.getUsername());
        view.setPhone(user.getPhone());
        view.setGroup(user.getGroup());
        view.setLevel(user.getLevel());
        view.setGroupView(user.getGroup().getDesc());
        view.setLevelView(user.getLevel().getDesc());
        view.setRealName(user.getRealName());
        view.setEmail(user.getEmail());
        view.setAvatar(user.getAvatar());
        view.setStatus(user.getStatus());
        view.setStatusView(user.getStatus().getDesc());
        return view;
    }
}
