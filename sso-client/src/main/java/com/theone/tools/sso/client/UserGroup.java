package com.theone.tools.sso.client;

/**
 * @author chenxiaotong
 */

public enum UserGroup {
    /**
     *
     */
    UNKNOWN("未指定"),
    HR("人力"),
    RD("研发"),
    UI("设计"),
    QA("测试"),
    OP("运维"),
    OM("运营"),
    BM("商务");

    String desc;

    UserGroup(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
