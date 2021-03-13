package com.theone.tools.waterfall.vo;

import java.util.List;

public class UserListResp {
    private int count;
    private List<UserInfoResp> list;

    public UserListResp() {
    }

    public UserListResp(int count, List<UserInfoResp> list) {
        this.count = count;
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<UserInfoResp> getList() {
        return list;
    }

    public void setList(List<UserInfoResp> list) {
        this.list = list;
    }
}
