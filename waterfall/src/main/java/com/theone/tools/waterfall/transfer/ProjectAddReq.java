package com.theone.tools.waterfall.transfer;

import java.util.List;

/**
 * @author chenxiaotong
 */
public class ProjectAddReq {

    private String name;

    private String desc;

    private List<String> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
}
