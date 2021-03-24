package com.theone.tools.waterfall.model.requirement;

/**
 * @author chenxiaotong
 */

public enum StageStatus {
    /**
     *
     */
    NOT_ACTIVE("未激活"),
    WAITING("等待中"),
    DOING("进行中"),
    DONE("完成"),
    REJECT("驳回");

    String desc;

    StageStatus(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
