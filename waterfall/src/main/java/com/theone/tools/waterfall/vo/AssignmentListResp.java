package com.theone.tools.waterfall.vo;

import java.util.List;

/**
 * @author chenxiaotong
 */
public class AssignmentListResp {

    private int count;
    private List<AssignmentInfoResp> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<AssignmentInfoResp> getList() {
        return list;
    }

    public void setList(List<AssignmentInfoResp> list) {
        this.list = list;
    }
}
