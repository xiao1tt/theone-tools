package com.theone.tools.waterfall.vo;

import java.util.List;

/**
 * @author chenxiaotong
 */
public class ProjectGroupListResp {
    private int count;
    private List<ProjectGroupInfoResp> list;

    public ProjectGroupListResp() {
    }

    public ProjectGroupListResp(int count, List<ProjectGroupInfoResp> list) {
        this.count = count;
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ProjectGroupInfoResp> getList() {
        return list;
    }

    public void setList(List<ProjectGroupInfoResp> list) {
        this.list = list;
    }
}
