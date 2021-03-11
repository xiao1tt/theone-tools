package com.theone.tools.horde.bean;

import java.util.List;

public class UserListView {
    private int count;
    private List<UserView> list;

    public UserListView() {
    }

    public UserListView(int count, List<UserView> list) {
        this.count = count;
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<UserView> getList() {
        return list;
    }

    public void setList(List<UserView> list) {
        this.list = list;
    }
}
