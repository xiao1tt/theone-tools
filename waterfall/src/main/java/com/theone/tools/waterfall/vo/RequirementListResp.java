package com.theone.tools.waterfall.vo;

import java.util.List;

/**
 * @author chenxiaotong
 */
public class RequirementListResp {

    private int count;

    private List<RequirementInfoResp> list;

    public RequirementListResp() {
    }

    public RequirementListResp(int count, List<RequirementInfoResp> list) {
        this.count = count;
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<RequirementInfoResp> getList() {
        return list;
    }

    public void setList(List<RequirementInfoResp> list) {
        this.list = list;
    }
}
