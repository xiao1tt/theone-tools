package com.theone.tools.waterfall.vo;

import java.util.List;

public class ProjectUserListResp {
    private int count;
    private List<ProjectUserInfoResp> list;

    public ProjectUserListResp() {
    }

    public ProjectUserListResp(int count, List<ProjectUserInfoResp> list) {
        this.count = count;
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ProjectUserInfoResp> getList() {
        return list;
    }

    public void setList(List<ProjectUserInfoResp> list) {
        this.list = list;
    }
}
