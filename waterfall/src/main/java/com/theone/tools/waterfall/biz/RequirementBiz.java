package com.theone.tools.waterfall.biz;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.theone.common.base.utils.DateFormatter;
import com.theone.tools.waterfall.model.requirement.*;
import com.theone.tools.waterfall.service.RequirementService;
import com.theone.tools.waterfall.vo.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * @author chenxiaotong
 */
@Service
public class RequirementBiz {
    @Resource
    private RequirementService requirementService;

    public void templateAdd(String name, String desc, List<TemplateStage> stages) {
        RequirementTemplate template = new RequirementTemplate();
        template.setName(name);
        template.setDesc(desc);
        template.setStages(stages);

        requirementService.templateAdd(template);
    }

    public void templateDelete(int templateId) {
        requirementService.templateDelete(templateId);
    }

    public void templateUpdate(int templateId, RequirementTemplateUpdateReq req) {
        RequirementTemplate template = new RequirementTemplate();
        template.setId(templateId);
        template.setName(req.getName());
        template.setDesc(req.getDesc());
        template.setStages(req.getStages());

        requirementService.templateUpdate(template);

    }

    public RequirementTemplateInfoResp templateInfo(int id) {
        RequirementTemplate template = requirementService.templateInfo(id);
        return adapt(template);
    }

    private RequirementTemplateInfoResp adapt(RequirementTemplate template) {
        if (template == null) {
            return null;
        }

        RequirementTemplateInfoResp resp = new RequirementTemplateInfoResp();
        resp.setId(template.getId());
        resp.setName(template.getName());
        resp.setDesc(template.getDesc());
        resp.setStages(template.getStages().stream().map(this::adapt).collect(Collectors.toList()));

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
        resp.setOrder(stage.getOrder());

        return resp;
    }

    public RequirementTemplateListResp templateList() {
        List<RequirementTemplate> requirementTemplates = requirementService.templateList();
        return new RequirementTemplateListResp(requirementTemplates.size(),
                requirementTemplates.stream().map(this::adapt).collect(Collectors.toList()));
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
        RequirementTemplate template = requirementService.templateInfo(req.getTemplateId());
        List<TemplateStage> templateStages = template.getStages();

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
            stage.setInProcess(false);
            stage.setTemplate(JSON.toJSONString(templateStage));
            stages.add(stage);
        }
        requirementService.stageAdd(stages);
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
        resp.setUpdateTime(requirement.getUpdateTime());
        resp.setCreateTime(requirement.getCreateTime());

        return resp;
    }

    public RequirementListResp list(Integer projectId, String username, RequirementStatus status) {
        List<RequirementAssignments> assignmentsList = requirementService.list(projectId, username, status);

        List<RequirementInfoResp> list = assignmentsList.stream().map(RequirementAssignments::getRequirement).map(this::adapt).collect(Collectors.toList());

        return new RequirementListResp(list.size(), list);
    }

    public RequirementDashboardResp dashboard(String username, RequirementStatus status) {
        Map<StageType, List<RequirementDashboardProject>> dashboardProjects = requirementService.dashboard(username, status);
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
}
