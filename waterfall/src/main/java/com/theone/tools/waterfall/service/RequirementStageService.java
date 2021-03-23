package com.theone.tools.waterfall.service;

import com.theone.tools.waterfall.dao.RequirementStageDao;
import com.theone.tools.waterfall.entity.RequirementStageEntity;
import com.theone.tools.waterfall.model.requirement.RequirementStage;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author chenxiaotong
 */
@Service
public class RequirementStageService {

    @Resource
    private AssignmentService assignmentService;
    @Resource
    private RequirementStageDao stageDao;

    public void add(List<RequirementStage> stages) {
        for (RequirementStage stage : stages) {
            RequirementStageEntity entity = adapt(stage);
            stageDao.insert(entity);

            assignmentService.init(entity);
        }
    }

    private RequirementStageEntity adapt(RequirementStage stage) {
        if (stage == null) {
            return null;
        }

        RequirementStageEntity entity = new RequirementStageEntity();

        entity.setId(stage.getId());
        entity.setProjectId(stage.getProjectId());
        entity.setRequirementId(stage.getRequirementId());
        entity.setTemplateId(stage.getTemplateId());
        entity.setTemplateStageId(stage.getTemplateStageId());
        entity.setName(stage.getName());
        entity.setStageDesc(stage.getStageDesc());
        entity.setType(stage.getType());
        entity.setStageOrder(stage.getStageOrder());
        entity.setRequiredGroup(stage.getRequiredGroup());
        entity.setDirector(stage.getDirector());
        entity.setStageStatus(stage.getStageStatus());
        entity.setTemplate(stage.getTemplate());
        entity.setUpdateTime(stage.getUpdateTime());
        entity.setCreateTime(stage.getCreateTime());

        return entity;
    }

    private RequirementStage adapt(RequirementStageEntity entity) {
        if (entity == null) {
            return null;
        }

        RequirementStage stage = new RequirementStage();
        stage.setId(entity.getId());
        stage.setProjectId(entity.getProjectId());
        stage.setRequirementId(entity.getRequirementId());
        stage.setTemplateId(entity.getTemplateId());
        stage.setTemplateStageId(entity.getTemplateStageId());
        stage.setName(entity.getName());
        stage.setStageDesc(entity.getStageDesc());
        stage.setType(entity.getType());
        stage.setStageOrder(entity.getStageOrder());
        stage.setRequiredGroup(entity.getRequiredGroup());
        stage.setDirector(entity.getDirector());
        stage.setStageStatus(entity.getStageStatus());
        stage.setTemplate(entity.getTemplate());
        stage.setUpdateTime(entity.getUpdateTime());
        stage.setCreateTime(entity.getCreateTime());

        return stage;
    }

    public List<RequirementStage> list(Integer requirementId) {
        RequirementStageEntity query = new RequirementStageEntity();
        query.setRequirementId(requirementId);

        return stageDao.queryAll(query).stream().map(this::adapt).collect(Collectors.toList());
    }

    public void deleteByRequirement(int requirementId) {
        stageDao.deleteByRequirement(requirementId);
    }

    public RequirementStage info(Integer id) {
        return adapt(stageDao.queryById(id));
    }
}
