package com.theone.tools.waterfall.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author chenxiaotong
 */
public class RequirementTemplateListResp implements Serializable {

    private int count;
    private List<RequirementTemplateInfoResp> list;

    public RequirementTemplateListResp() {
    }

    public RequirementTemplateListResp(int count, List<RequirementTemplateInfoResp> list) {
        this.count = count;
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<RequirementTemplateInfoResp> getList() {
        return list;
    }

    public void setList(List<RequirementTemplateInfoResp> list) {
        this.list = list;
    }
}
