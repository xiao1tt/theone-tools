package com.theone.tools.sso.client;

/**
 * @author chenxiaotong
 */

public enum UserStatus {
    /**
     *
     */
    AVAILABLE("可用"),
    WAIT_COMPLETED("信息待完善"),
    ;

    String desc;

    UserStatus(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
