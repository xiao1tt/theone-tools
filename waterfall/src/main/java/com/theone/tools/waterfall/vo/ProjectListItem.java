package com.theone.tools.waterfall.vo;

import java.util.List;

/**
 * @author chenxiaotong
 */
public class ProjectListItem {

    private int groupId;
    private String groupName;
    private List<ProjectInfoResp> projectList;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<ProjectInfoResp> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<ProjectInfoResp> projectList) {
        this.projectList = projectList;
    }
}
