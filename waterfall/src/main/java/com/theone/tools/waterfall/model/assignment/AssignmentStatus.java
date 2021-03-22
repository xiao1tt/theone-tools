package com.theone.tools.waterfall.model.assignment;

/**
 * @author chenxiaotong
 */

public enum AssignmentStatus {
    /**
     *
     */
    INIT("初始化"),
    WAIT_ALLOCATE("待分配"),
    WAITING("等待开始"),
    DOING("进行中"),
    DONE("已完成"),
    PENDING("暂停中");


    String desc;

    AssignmentStatus(String desc) {
        this.desc = desc;
    }
}
