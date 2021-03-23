package com.theone.tools.waterfall.model.assignment;

import java.util.List;

/**
 * @author chenxiaotong
 */
public class AssignmentStruct {

    private Assignment assignment;
    private List<AssignmentWorker> workers;

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public List<AssignmentWorker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<AssignmentWorker> workers) {
        this.workers = workers;
    }
}
