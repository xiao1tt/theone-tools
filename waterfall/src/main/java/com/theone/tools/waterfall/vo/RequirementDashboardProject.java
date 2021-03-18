package com.theone.tools.waterfall.vo;

import java.util.List;

/**
 * @author chenxiaotong
 */
public class RequirementDashboardProject {
    private int projectId;
    private String projectName;

    private List<RequirementDashboardRequirement> requirements;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<RequirementDashboardRequirement> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<RequirementDashboardRequirement> requirements) {
        this.requirements = requirements;
    }
}
