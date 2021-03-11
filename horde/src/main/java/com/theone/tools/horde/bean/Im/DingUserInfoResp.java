
package com.theone.tools.horde.bean.Im;

import com.theone.tools.oa.client.bean.OaBaseResp;

public class DingUserInfoResp extends OaBaseResp {

    private Boolean active;

    private String avatar;

    private Boolean exclusiveAccount;

    private Boolean isAdmin;

    private Boolean isBoss;

    private Boolean isHide;

    private Boolean isSenior;

    private String mobile;

    private String name;

    private String openId;

    private Boolean realAuthed;

    private String stateCode;

    private String unionid;

    private String userid;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Boolean getExclusiveAccount() {
        return exclusiveAccount;
    }

    public void setExclusiveAccount(Boolean exclusiveAccount) {
        this.exclusiveAccount = exclusiveAccount;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Boolean getIsBoss() {
        return isBoss;
    }

    public void setIsBoss(Boolean isBoss) {
        this.isBoss = isBoss;
    }

    public Boolean getIsHide() {
        return isHide;
    }

    public void setIsHide(Boolean isHide) {
        this.isHide = isHide;
    }

    public Boolean getIsSenior() {
        return isSenior;
    }

    public void setIsSenior(Boolean isSenior) {
        this.isSenior = isSenior;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Boolean getRealAuthed() {
        return realAuthed;
    }

    public void setRealAuthed(Boolean realAuthed) {
        this.realAuthed = realAuthed;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

}
