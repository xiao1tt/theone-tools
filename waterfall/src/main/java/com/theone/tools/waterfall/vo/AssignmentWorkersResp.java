package com.theone.tools.waterfall.vo;

import java.util.List;

public class AssignmentWorkersResp {

    private List<String> workers;

    public AssignmentWorkersResp() {
    }

    public AssignmentWorkersResp(List<String> workers) {
        this.workers = workers;
    }

    public List<String> getWorkers() {
        return workers;
    }

    public void setWorkers(List<String> workers) {
        this.workers = workers;
    }
}
