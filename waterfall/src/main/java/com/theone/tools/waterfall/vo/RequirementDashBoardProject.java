package com.theone.tools.waterfall.vo;

import java.util.List;

/**
 * @author chenxiaotong
 */
public class RequirementDashBoardProject {
    private int projectId;
    private String projectName;

    private List<RequirementDashBoardRequirement> requirements;

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

    public List<RequirementDashBoardRequirement> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<RequirementDashBoardRequirement> requirements) {
        this.requirements = requirements;
    }
}
