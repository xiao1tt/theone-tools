package com.theone.tools.waterfall.vo;

import java.util.List;

/**
 * @author chenxiaotong
 */
public class RequirementDashboardGroup {
    private String groupName;
    private List<RequirementDashBoardProject> projects;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<RequirementDashBoardProject> getProjects() {
        return projects;
    }

    public void setProjects(List<RequirementDashBoardProject> projects) {
        this.projects = projects;
    }
}
