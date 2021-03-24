package com.theone.tools.waterfall.biz;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.theone.common.base.utils.DateFormatter;
import com.theone.tools.waterfall.model.assignment.AssignmentStruct;
import com.theone.tools.waterfall.model.requirement.Requirement;
import com.theone.tools.waterfall.model.requirement.RequirementStage;
import com.theone.tools.waterfall.model.requirement.RequirementStatus;
import com.theone.tools.waterfall.model.requirement.RequirementStruct;
import com.theone.tools.waterfall.model.requirement.RequirementTemplate;
import com.theone.tools.waterfall.model.requirement.RequirementTemplateStruct;
import com.theone.tools.waterfall.model.requirement.StageStatus;
import com.theone.tools.waterfall.model.requirement.StageType;
import com.theone.tools.waterfall.model.requirement.TemplateStage;
import com.theone.tools.waterfall.service.AssignmentService;
import com.theone.tools.waterfall.service.RequirementService;
import com.theone.tools.waterfall.service.RequirementStageService;
import com.theone.tools.waterfall.service.RequirementTemplateService;
import com.theone.tools.waterfall.vo.AssignmentInfoResp;
import com.theone.tools.waterfall.vo.RequirementAddReq;
import com.theone.tools.waterfall.vo.RequirementDashboardGroup;
import com.theone.tools.waterfall.vo.RequirementDashboardProject;
import com.theone.tools.waterfall.vo.RequirementDashboardResp;
import com.theone.tools.waterfall.vo.RequirementInfoResp;
import com.theone.tools.waterfall.vo.RequirementListResp;
import com.theone.tools.waterfall.vo.RequirementStageInfoResp;
import com.theone.tools.waterfall.vo.RequirementStagesResp;
import com.theone.tools.waterfall.vo.RequirementTemplateInfoResp;
import com.theone.tools.waterfall.vo.RequirementTemplateListResp;
import com.theone.tools.waterfall.vo.RequirementTemplateSimpleResp;
import com.theone.tools.waterfall.vo.TemplateStageInfoResp;
import com.theone.tools.waterfall.vo.TemplateStageSaveReq;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author chenxiaotong
 */
@Service
public class RequirementBiz {

    @Resource
    private AssignmentBiz assignmentBiz;
    @Resource
    private RequirementService requirementService;
    @Resource
    private RequirementTemplateService requirementTemplateService;
    @Resource
    private RequirementStageService requirementStageService;
    @Resource
    private AssignmentService assignmentService;

    public void templateAdd(String name, String desc, List<TemplateStageSaveReq> stages) {
        RequirementTemplate template = new RequirementTemplate();
        template.setName(name);
        template.setDesc(desc);

        List<TemplateStage> stageList = stages.stream().map(this::adapt).collect(Collectors.toList());

        requirementTemplateService.add(template, stageList);
    }

    private TemplateStage adapt(TemplateStageSaveReq templateStageAddReq) {
        TemplateStage stage = new TemplateStage();
        stage.setName(templateStageAddReq.getName());
        stage.setDesc(templateStageAddReq.getDesc());
        stage.setRequiredGroup(templateStageAddReq.getRequiredGroup());
        stage.setType(templateStageAddReq.getType());
        stage.setDirector(templateStageAddReq.getDirector());
        stage.setOrder(templateStageAddReq.getOrder());
        return stage;
    }

    public void templateDelete(int templateId) {
        requirementTemplateService.delete(templateId);
    }

    public void templateUpdate(Integer templateId, String name, String desc, List<TemplateStageSaveReq> stages) {
        RequirementTemplate template = new RequirementTemplate();
        template.setId(templateId);
        template.setName(name);
        template.setDesc(desc);

        List<TemplateStage> stageList = stages.stream().map(this::adapt).collect(Collectors.toList());

        requirementTemplateService.update(template, stageList);
    }

    public RequirementTemplateInfoResp templateInfo(int id) {
        RequirementTemplateStruct struct = requirementTemplateService.struct(id);
        return adapt(struct);
    }

    private RequirementTemplateInfoResp adapt(RequirementTemplateStruct struct) {
        if (struct == null) {
            return null;
        }

        RequirementTemplate template = struct.getTemplate();
        List<TemplateStage> stages = struct.getStages();

        RequirementTemplateInfoResp resp = new RequirementTemplateInfoResp();
        resp.setId(template.getId());
        resp.setName(template.getName());
        resp.setDesc(template.getDesc());
        resp.setStages(stages.stream().map(this::adapt).collect(Collectors.toList()));

        return resp;
    }


    private TemplateStageInfoResp adapt(TemplateStage stage) {
        if (stage == null) {
            return null;
        }

        TemplateStageInfoResp resp = new TemplateStageInfoResp();

        resp.setId(stage.getId());
        resp.setTemplateId(stage.getTemplateId());
        resp.setName(stage.getName());
        resp.setDesc(stage.getDesc());
        resp.setRequiredGroup(stage.getRequiredGroup());
        resp.setRequiredGroupView(stage.getRequiredGroup().getDesc());
        resp.setType(stage.getType());
        resp.setTypeView(stage.getType().getDesc());
        resp.setDirector(stage.getDirector());
        resp.setOrder(stage.getOrder());

        return resp;
    }

    public RequirementTemplateListResp templateList() {
        List<RequirementTemplate> templateList = requirementTemplateService.list();
        List<RequirementTemplateSimpleResp> list = templateList.stream().map(this::adapt).collect(Collectors.toList());

        RequirementTemplateListResp resp = new RequirementTemplateListResp();
        resp.setCount(list.size());
        resp.setList(list);

        return resp;
    }

    private RequirementTemplateSimpleResp adapt(RequirementTemplate template) {
        if (template == null) {
            return null;
        }

        RequirementTemplateSimpleResp resp = new RequirementTemplateSimpleResp();
        resp.setId(template.getId());
        resp.setName(template.getName());
        resp.setDesc(template.getDesc());

        return resp;
    }

    public void add(RequirementAddReq req) {
        Requirement add = new Requirement();
        add.setProjectId(req.getProjectId());
        add.setTemplateId(req.getTemplateId());
        add.setName(req.getName());
        add.setRequirementDesc(req.getDesc());
        add.setRequirementOwner(req.getOwner());
        add.setExpectDate(DateFormatter.parseDate(req.getExpectDate()));
        add.setPriority(req.getPriority());
        add.setRequirementStatus(RequirementStatus.NOT_ACTIVE);

        Requirement requirement = requirementService.add(add);

        List<TemplateStage> templateStages = requirementTemplateService.stageList(req.getTemplateId());

        List<RequirementStage> stages = Lists.newArrayList();

        for (TemplateStage templateStage : templateStages) {
            RequirementStage stage = new RequirementStage();
            stage.setProjectId(req.getProjectId());
            stage.setRequirementId(requirement.getId());
            stage.setTemplateId(req.getTemplateId());
            stage.setTemplateStageId(templateStage.getId());
            stage.setName(templateStage.getName());
            stage.setStageDesc(templateStage.getDesc());
            stage.setStageOrder(templateStage.getOrder());
            stage.setDirector(templateStage.getDirector());
            stage.setRequiredGroup(templateStage.getRequiredGroup());
            stage.setType(templateStage.getType());
            stage.setStageStatus(StageStatus.NOT_ACTIVE);
            stage.setTemplate(JSON.toJSONString(templateStage));
            stages.add(stage);
        }
        requirementStageService.add(stages);
    }

    public void delete(int id) {
        requirementService.delete(id);
    }

    public void update(int id, String name, String desc) {
        Requirement requirement = new Requirement();
        requirement.setId(id);
        requirement.setName(name);
        requirement.setRequirementDesc(desc);

        requirementService.update(requirement);
    }

    public RequirementInfoResp info(int id) {
        return adapt(requirementService.info(id));
    }

    private RequirementInfoResp adapt(Requirement requirement) {
        if (requirement == null) {
            return null;
        }
        RequirementInfoResp resp = new RequirementInfoResp();
        resp.setId(requirement.getId());
        resp.setProjectId(requirement.getProjectId());
        resp.setTemplateId(requirement.getTemplateId());
        resp.setName(requirement.getName());
        resp.setDesc(requirement.getRequirementDesc());
        resp.setOwner(requirement.getRequirementOwner());
        resp.setCreateTime(DateFormatter.format(requirement.getCreateTime()));

        return resp;
    }

    public RequirementListResp list(Integer projectId, String username, RequirementStatus status) {
        List<RequirementStruct> assignmentsList = requirementService.listStruct(projectId, username, status);

        List<RequirementInfoResp> list = assignmentsList.stream().map(RequirementStruct::getRequirement)
                .map(this::adapt).collect(Collectors.toList());

        return new RequirementListResp(list.size(), list);
    }

    public RequirementDashboardResp dashboard(String username, RequirementStatus status) {
        Map<StageType, List<RequirementDashboardProject>> dashboardProjects = requirementService
                .dashboard(username, status);
        List<RequirementDashboardGroup> groups = new ArrayList<>();

        dashboardProjects.forEach((stageType, requirementDashboardProjects) -> {
            RequirementDashboardGroup group = new RequirementDashboardGroup();
            group.setGroupName(stageType == null ? "未开始" : stageType.getDesc());
            group.setProjects(requirementDashboardProjects);
            groups.add(group);
        });
        RequirementDashboardResp resp = new RequirementDashboardResp();
        resp.setGroups(groups);

        return resp;
    }

    public RequirementStagesResp stages(Integer requirementId) {
        List<RequirementStage> stageList = requirementStageService.list(requirementId);

        List<AssignmentStruct> assignmentStructList = assignmentService.listStructByRequirement(requirementId);

        Map<Integer, List<AssignmentStruct>> stageAssignmentMap = assignmentStructList.stream()
                .collect(Collectors.groupingBy(assignmentStruct -> assignmentStruct.getAssignment().getStageId()));

        List<RequirementStageInfoResp> infoRespList = new ArrayList<>();

        for (RequirementStage requirementStage : stageList) {
            List<AssignmentStruct> assignmentStructs = stageAssignmentMap.get(requirementStage.getId());

            RequirementStageInfoResp infoResp = assembleResp(requirementStage, assignmentStructs);

            infoRespList.add(infoResp);
        }

        RequirementStagesResp resp = new RequirementStagesResp();
        resp.setStageList(infoRespList);
        return resp;
    }

    public RequirementStageInfoResp assembleResp(RequirementStage requirementStage,
            List<AssignmentStruct> assignmentStructs) {
        RequirementStageInfoResp infoResp = new RequirementStageInfoResp();
        infoResp.setId(requirementStage.getId());
        infoResp.setProjectId(requirementStage.getProjectId());
        infoResp.setRequirementId(requirementStage.getRequirementId());
        infoResp.setTemplateId(requirementStage.getTemplateId());
        infoResp.setTemplateStageId(requirementStage.getTemplateStageId());
        infoResp.setName(requirementStage.getName());
        infoResp.setType(requirementStage.getType());
        infoResp.setTypeView(requirementStage.getType().getDesc());
        infoResp.setStageDesc(requirementStage.getStageDesc());
        infoResp.setStageOrder(requirementStage.getStageOrder());
        infoResp.setRequiredGroup(requirementStage.getRequiredGroup());
        infoResp.setRequiredGroupView(requirementStage.getRequiredGroup().getDesc());
        infoResp.setDirector(requirementStage.getDirector());
        infoResp.setStageStatus(requirementStage.getStageStatus());
        infoResp.setStageStatusView(requirementStage.getStageStatus().getDesc());

        List<AssignmentInfoResp> assignmentInfoRespList = Lists.newArrayList();

        for (AssignmentStruct assignmentStruct : assignmentStructs) {
            AssignmentInfoResp assignmentInfoResp = assignmentBiz.assembleResp(assignmentStruct);
            assignmentInfoRespList.add(assignmentInfoResp);
        }

        infoResp.setAssignmentList(assignmentInfoRespList);
        return infoResp;
    }
}
