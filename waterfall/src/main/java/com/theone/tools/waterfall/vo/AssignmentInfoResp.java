package com.theone.tools.waterfall.vo;

import com.theone.tools.waterfall.model.assignment.AssignmentStatus;
import java.util.List;

/**
 * @author chenxiaotong
 */
public class AssignmentInfoResp {

    private Integer id;

    private Integer projectId;

    private Integer requirementId;

    private Integer stageId;

    private String name;

    private String assignmentDesc;

    private AssignmentStatus assignmentStatus;

    private String assignmentStatusView;

    private String expectTime;

    private List<AssignmentWorkerInfoResp> workerList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(Integer requirementId) {
        this.requirementId = requirementId;
    }

    public Integer getStageId() {
        return stageId;
    }

    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAssignmentDesc() {
        return assignmentDesc;
    }

    public void setAssignmentDesc(String assignmentDesc) {
        this.assignmentDesc = assignmentDesc;
    }

    public AssignmentStatus getAssignmentStatus() {
        return assignmentStatus;
    }

    public void setAssignmentStatus(AssignmentStatus assignmentStatus) {
        this.assignmentStatus = assignmentStatus;
    }

    public String getAssignmentStatusView() {
        return assignmentStatusView;
    }

    public void setAssignmentStatusView(String assignmentStatusView) {
        this.assignmentStatusView = assignmentStatusView;
    }

    public String getExpectTime() {
        return expectTime;
    }

    public void setExpectTime(String expectTime) {
        this.expectTime = expectTime;
    }

    public List<AssignmentWorkerInfoResp> getWorkerList() {
        return workerList;
    }

    public void setWorkerList(List<AssignmentWorkerInfoResp> workerList) {
        this.workerList = workerList;
    }
}
