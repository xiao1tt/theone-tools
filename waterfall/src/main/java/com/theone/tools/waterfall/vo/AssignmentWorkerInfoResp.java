package com.theone.tools.waterfall.vo;

import com.theone.tools.waterfall.model.assignment.AssignmentStatus;

public class AssignmentWorkerInfoResp {

    private String worker;

    private AssignmentStatus workStatus;

    private String workStatusView;

    private String startTime;

    private String completeTime;

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public AssignmentStatus getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(AssignmentStatus workStatus) {
        this.workStatus = workStatus;
    }

    public String getWorkStatusView() {
        return workStatusView;
    }

    public void setWorkStatusView(String workStatusView) {
        this.workStatusView = workStatusView;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(String completeTime) {
        this.completeTime = completeTime;
    }
}
