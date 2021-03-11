package com.theone.tools.sso.client;

/**
 * @author chenxiaotong
 */

public enum UserLevel {
    /**
     *
     */
    UNKNOWN("未指定", 0),
    STAFF("普通员工", 1),
    MANAGER("经理", 8),
    DIRECTOR("负责人", 10);

    String desc;
    int rank;

    UserLevel(String desc, int rank) {
        this.desc = desc;
        this.rank = rank;
    }

    public String getDesc() {
        return desc;
    }

    public boolean isManager() {
        return this.rank >= UserLevel.MANAGER.rank;
    }

    public boolean isDirector() {
        return this.rank >= UserLevel.DIRECTOR.rank;
    }
}
