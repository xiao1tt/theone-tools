package com.theone.tools.waterfall.vo;

import java.util.List;

/**
 * @author chenxiaotong
 */
public class RequirementDashboardGroup {

    private String groupName;
    private List<RequirementDashboardProject> projects;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<RequirementDashboardProject> getProjects() {
        return projects;
    }

    public void setProjects(List<RequirementDashboardProject> projects) {
        this.projects = projects;
    }
}
