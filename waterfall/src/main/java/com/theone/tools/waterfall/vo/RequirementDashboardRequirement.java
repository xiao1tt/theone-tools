package com.theone.tools.waterfall.vo;

import com.theone.tools.waterfall.model.requirement.RequirementStatus;
import com.theone.tools.waterfall.model.requirement.StageType;

import java.util.List;

/**
 * @author chenxiaotong
 */
public class RequirementDashboardRequirement {
    private String name;
    private String desc;

    private int priority;
    private String owner;
    private String expectDate;

    private RequirementStatus status;
    private String statusView;

    private StageType currentStage;
    private String currentStageView;

    private List<String> currentWorker;

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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getExpectDate() {
        return expectDate;
    }

    public void setExpectDate(String expectDate) {
        this.expectDate = expectDate;
    }

    public RequirementStatus getStatus() {
        return status;
    }

    public void setStatus(RequirementStatus status) {
        this.status = status;
    }

    public String getStatusView() {
        return statusView;
    }

    public void setStatusView(String statusView) {
        this.statusView = statusView;
    }

    public StageType getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(StageType currentStage) {
        this.currentStage = currentStage;
    }

    public String getCurrentStageView() {
        return currentStageView;
    }

    public void setCurrentStageView(String currentStageView) {
        this.currentStageView = currentStageView;
    }

    public List<String> getCurrentWorker() {
        return currentWorker;
    }

    public void setCurrentWorker(List<String> currentWorker) {
        this.currentWorker = currentWorker;
    }
}
