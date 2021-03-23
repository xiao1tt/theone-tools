package com.theone.tools.waterfall.vo;

import com.theone.tools.sso.client.UserGroup;
import com.theone.tools.waterfall.model.requirement.StageStatus;
import com.theone.tools.waterfall.model.requirement.StageType;
import java.util.List;

/**
 * @author chenxiaotong
 */
public class RequirementStageInfoResp {

    private Integer id;

    private Integer projectId;

    private Integer requirementId;

    private Integer templateId;

    private Integer templateStageId;

    private String name;

    private StageType type;

    private String typeView;

    private String stageDesc;

    private Integer stageOrder;

    private UserGroup requiredGroup;

    private String requiredGroupView;

    private String director;

    private StageStatus stageStatus;

    private String stageStatusView;

    private List<AssignmentInfoResp> assignmentList;

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

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public Integer getTemplateStageId() {
        return templateStageId;
    }

    public void setTemplateStageId(Integer templateStageId) {
        this.templateStageId = templateStageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StageType getType() {
        return type;
    }

    public void setType(StageType type) {
        this.type = type;
    }

    public String getTypeView() {
        return typeView;
    }

    public void setTypeView(String typeView) {
        this.typeView = typeView;
    }

    public String getStageDesc() {
        return stageDesc;
    }

    public void setStageDesc(String stageDesc) {
        this.stageDesc = stageDesc;
    }

    public Integer getStageOrder() {
        return stageOrder;
    }

    public void setStageOrder(Integer stageOrder) {
        this.stageOrder = stageOrder;
    }

    public UserGroup getRequiredGroup() {
        return requiredGroup;
    }

    public void setRequiredGroup(UserGroup requiredGroup) {
        this.requiredGroup = requiredGroup;
    }

    public String getRequiredGroupView() {
        return requiredGroupView;
    }

    public void setRequiredGroupView(String requiredGroupView) {
        this.requiredGroupView = requiredGroupView;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public StageStatus getStageStatus() {
        return stageStatus;
    }

    public void setStageStatus(StageStatus stageStatus) {
        this.stageStatus = stageStatus;
    }

    public String getStageStatusView() {
        return stageStatusView;
    }

    public void setStageStatusView(String stageStatusView) {
        this.stageStatusView = stageStatusView;
    }

    public List<AssignmentInfoResp> getAssignmentList() {
        return assignmentList;
    }

    public void setAssignmentList(List<AssignmentInfoResp> assignmentList) {
        this.assignmentList = assignmentList;
    }
}
