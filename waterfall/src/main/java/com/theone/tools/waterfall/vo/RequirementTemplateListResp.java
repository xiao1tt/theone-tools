package com.theone.tools.waterfall.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author chenxiaotong
 */
public class RequirementTemplateListResp implements Serializable {

    private int count;
    private List<RequirementTemplateSimpleResp> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<RequirementTemplateSimpleResp> getList() {
        return list;
    }

    public void setList(List<RequirementTemplateSimpleResp> list) {
        this.list = list;
    }
}
