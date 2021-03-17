package com.theone.tools.waterfall.model.requirement;

/**
 * @author chenxiaotong
 */

public enum StageType {
    /**
     *
     */
    BUSINESS("商务阶段"),
    OPERATION("运营阶段"),
    REQUIREMENT("需求阶段"),
    DESIGN("设计阶段"),
    DEVELOPMENT("开发阶段"),
    TESTING("测试阶段"),
    COMPLETE("完成");

    String desc;

    StageType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
