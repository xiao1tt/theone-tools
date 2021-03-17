package com.theone.tools.waterfall.service;

import com.google.common.collect.Lists;
import com.theone.tools.waterfall.dao.*;
import com.theone.tools.waterfall.entity.RequirementEntity;
import com.theone.tools.waterfall.entity.RequirementStageEntity;
import com.theone.tools.waterfall.entity.RequirementTemplateEntity;
import com.theone.tools.waterfall.entity.RequirementTemplateStageEntity;
import com.theone.tools.waterfall.model.assignment.Assignment;
import com.theone.tools.waterfall.model.assignment.AssignmentStatus;
import com.theone.tools.waterfall.model.requirement.*;
import com.theone.tools.waterfall.vo.RequirementDashBoardProject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author chenxiaotong
 */
@Service
public class RequirementService {
    @Resource
    private AssignmentService assignmentService;
    @Resource
    private UserService userService;
    @Resource
    private ProjectService projectService;
    @Resource
    private RequirementDao requirementDao;
    @Resource
    private RequirementStageDao stageDao;
    @Resource
    private RequirementAttachDao attachDao;
    @Resource
    private RequirementTemplateDao templateDao;
    @Resource
    private RequirementTemplateStageDao templateStageDao;

    public void templateAdd(RequirementTemplate template) {
        RequirementTemplateEntity insert = adapt(template);
        templateDao.insert(insert);
        templateStageDao.insertAll(insert.getId(), template.getStages().stream().map(this::adapt).collect(Collectors.toList()));
    }

    private RequirementTemplateEntity adapt(RequirementTemplate template) {
        if (template == null) {
            return null;
        }

        RequirementTemplateEntity entity = new RequirementTemplateEntity();
        entity.setName(template.getName());
        entity.setTemplateDesc(template.getDesc());

        return entity;
    }

    private RequirementTemplateStageEntity adapt(TemplateStage stage) {
        if (stage == null) {
            return null;
        }

        RequirementTemplateStageEntity entity = new RequirementTemplateStageEntity();
        entity.setId(stage.getId());
        entity.setTemplateId(stage.getTemplateId());
        entity.setName(stage.getName());
        entity.setStageDesc(stage.getDesc());
        entity.setStageOrder(stage.getOrder());
        entity.setRequiredGroup(stage.getRequiredGroup());

        return entity;
    }

    public void templateDelete(int templateId) {
        templateDao.deleteById(templateId);
        templateStageDao.deleteByTemplate(templateId);
    }

    public void templateUpdate(RequirementTemplate template) {
        templateDao.update(adapt(template));
        templateStageDao.deleteByTemplate(template.getId());
        templateStageDao.insertAll(template.getId(), template.getStages().stream().map(this::adapt).collect(Collectors.toList()));
    }

    public RequirementTemplate templateInfo(int id) {
        RequirementTemplateEntity entity = templateDao.queryById(id);
        RequirementTemplateStageEntity query = new RequirementTemplateStageEntity();
        query.setTemplateId(id);
        List<RequirementTemplateStageEntity> stages = templateStageDao.queryAll(query);
        return adapt(entity, stages);
    }

    private RequirementTemplate adapt(RequirementTemplateEntity entity) {
        if (entity == null) {
            return null;
        }

        RequirementTemplate template = new RequirementTemplate();
        template.setId(entity.getId());
        template.setName(entity.getName());
        template.setDesc(entity.getTemplateDesc());

        return template;
    }

    private RequirementTemplate adapt(RequirementTemplateEntity entity, List<RequirementTemplateStageEntity> stages) {
        if (entity == null) {
            return null;
        }

        RequirementTemplate template = new RequirementTemplate();
        template.setId(entity.getId());
        template.setName(entity.getName());
        template.setDesc(entity.getTemplateDesc());
        template.setStages(stages.stream().map(this::adapt).collect(Collectors.toList()));

        return template;
    }

    private TemplateStage adapt(RequirementTemplateStageEntity entity) {
        if (entity == null) {
            return null;
        }

        TemplateStage stage = new TemplateStage();
        stage.setId(entity.getId());
        stage.setTemplateId(entity.getTemplateId());
        stage.setName(entity.getName());
        stage.setDesc(entity.getStageDesc());
        stage.setRequiredGroup(entity.getRequiredGroup());
        stage.setDirector(entity.getDirector());
        stage.setOrder(entity.getStageOrder());

        return stage;
    }

    public List<RequirementTemplate> templateList() {
        List<RequirementTemplateEntity> templateEntities = templateDao.queryAllByLimit(0, 100);
        return templateEntities.stream().map(this::adapt).collect(Collectors.toList());
    }

    public Requirement add(Requirement requirement) {
        RequirementEntity insert = adapt(requirement);
        requirementDao.insert(insert);
        return adapt(requirementDao.queryById(insert.getId()));
    }

    private Requirement adapt(RequirementEntity entity) {
        if (entity == null) {
            return null;
        }
        Requirement requirement = new Requirement();
        requirement.setId(entity.getId());
        requirement.setProjectId(entity.getProjectId());
        requirement.setTemplateId(entity.getTemplateId());
        requirement.setName(entity.getName());
        requirement.setRequirementDesc(entity.getRequirementDesc());
        requirement.setRequirementOwner(entity.getRequirementOwner());
        requirement.setPriority(entity.getPriority());
        requirement.setExpectDate(entity.getExpectDate());
        requirement.setUpdateTime(entity.getUpdateTime());
        requirement.setCreateTime(entity.getCreateTime());

        return requirement;
    }

    private RequirementEntity adapt(Requirement requirement) {
        if (requirement == null) {
            return null;
        }
        RequirementEntity entity = new RequirementEntity();
        entity.setProjectId(requirement.getProjectId());
        entity.setTemplateId(requirement.getTemplateId());
        entity.setName(requirement.getName());
        entity.setPriority(requirement.getPriority());
        entity.setExpectDate(requirement.getExpectDate());
        entity.setRequirementDesc(requirement.getRequirementDesc());
        entity.setRequirementOwner(requirement.getRequirementOwner());

        return entity;
    }

    public void stageAdd(List<RequirementStage> stages) {
        for (RequirementStage stage : stages) {
            RequirementStageEntity entity = adapt(stage);
            stageDao.insert(entity);

            Assignment assignment = new Assignment();
            assignment.setProjectId(entity.getProjectId());
            assignment.setRequirementId(entity.getRequirementId());
            assignment.setStageId(entity.getId());
            assignment.setStageType(entity.getType());
            assignment.setName(entity.getName());
            assignment.setAssignmentDesc("默认任务");
            assignment.setAssignmentStatus(AssignmentStatus.INIT);
            assignmentService.add(assignment);
        }
    }

    private RequirementStageEntity adapt(RequirementStage stage) {
        if (stage == null) {
            return null;
        }

        RequirementStageEntity entity = new RequirementStageEntity();
        entity.setProjectId(stage.getProjectId());
        entity.setRequirementId(stage.getRequirementId());
        entity.setTemplateId(stage.getTemplateId());
        entity.setTemplateStageId(stage.getTemplateStageId());
        entity.setName(stage.getName());
        entity.setStageDesc(stage.getStageDesc());
        entity.setStageOrder(stage.getStageOrder());
        entity.setRequiredGroup(stage.getRequiredGroup());
        entity.setTemplate(stage.getTemplate());

        return entity;
    }

    public void delete(int id) {
        requirementDao.deleteById(id);
        stageDao.deleteByRequirement(id);
        attachDao.deleteByRequirement(id);
    }

    public void update(Requirement requirement) {
        requirementDao.update(adapt(requirement));
    }

    public Requirement info(int id) {
        RequirementEntity entity = requirementDao.queryById(id);
        return adapt(entity);
    }

    public List<RequirementAssignments> list(Integer projectId, String username, RequirementStatus status) {
        List<Assignment> assignments = assignmentService.list(projectId, username);

        Map<Integer, List<Assignment>> requirementAssignmentMap = assignments.stream()
                .collect(Collectors.groupingBy(Assignment::getProjectId, HashMap::new, Collectors.toCollection(ArrayList::new)));

        List<Requirement> requirements = this.listByIds(requirementAssignmentMap.keySet(), status);

        Map<Integer, Requirement> requirementMap = requirements.stream()
                .collect(Collectors.toMap(Requirement::getId, Function.identity()));

        List<RequirementAssignments> result = new ArrayList<>();

        for (Integer id : requirementMap.keySet()) {
            RequirementAssignments item = new RequirementAssignments();
            item.setRequirement(requirementMap.get(id));
            item.setAssignments(requirementAssignmentMap.get(id));
            result.add(item);
        }

        return result;
    }

    private List<Requirement> listByIds(Collection<Integer> requirementIds, RequirementStatus status) {
        return requirementDao.queryByIds(requirementIds, status).stream().map(this::adapt).collect(Collectors.toList());
    }

    public Map<StageType, List<RequirementDashBoardProject>> dashboard(String username, RequirementStatus status) {
        RequirementEntity query = new RequirementEntity();
        query.setRequirementStatus(status);

        List<RequirementEntity> requirementEntities = requirementDao.queryAll(query);
        if (StringUtils.isNotEmpty(username)) {
            List<Assignment> assignments = assignmentService.list(username);


        }
    }
}
