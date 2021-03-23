package com.theone.tools.waterfall.model.requirement;

import com.theone.tools.waterfall.model.assignment.Assignment;
import java.util.List;

/**
 * @author chenxiaotong
 */
public class RequirementStruct {

    private Requirement requirement;
    private List<Assignment> assignments;

    public Requirement getRequirement() {
        return requirement;
    }

    public void setRequirement(Requirement requirement) {
        this.requirement = requirement;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }
}
