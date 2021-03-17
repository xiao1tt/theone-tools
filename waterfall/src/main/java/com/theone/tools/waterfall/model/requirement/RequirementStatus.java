package com.theone.tools.waterfall.model.requirement;

/**
 * @author chenxiaotong
 */
public enum RequirementStatus {
    /**
     *
     */
    NOT_ACTIVE("未开始"),
    DOING("进行中"),
    CLOSE("关闭");

    String desc;

    RequirementStatus(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
