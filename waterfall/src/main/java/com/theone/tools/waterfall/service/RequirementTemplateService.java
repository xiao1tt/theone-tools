package com.theone.tools.waterfall.service;

import com.theone.tools.waterfall.dao.RequirementTemplateDao;
import com.theone.tools.waterfall.dao.RequirementTemplateStageDao;
import com.theone.tools.waterfall.entity.RequirementTemplateEntity;
import com.theone.tools.waterfall.entity.RequirementTemplateStageEntity;
import com.theone.tools.waterfall.model.requirement.RequirementTemplate;
import com.theone.tools.waterfall.model.requirement.TemplateStage;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author chenxiaotong
 */
@Service
public class RequirementTemplateService {

    @Resource
    private RequirementTemplateDao templateDao;
    @Resource
    private RequirementTemplateStageDao templateStageDao;

    public void add(RequirementTemplate template) {
        RequirementTemplateEntity insert = adapt(template);
        templateDao.insert(insert);
        templateStageDao
                .insertAll(insert.getId(), template.getStages().stream().map(this::adapt).collect(Collectors.toList()));
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

    public void delete(int templateId) {
        templateDao.deleteById(templateId);
        templateStageDao.deleteByTemplate(templateId);
    }

    public void update(RequirementTemplate template) {
        templateDao.update(adapt(template));
        templateStageDao.deleteByTemplate(template.getId());
        templateStageDao.insertAll(template.getId(),
                template.getStages().stream().map(this::adapt).collect(Collectors.toList()));
    }

    public RequirementTemplate info(int id) {
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
        stage.setType(entity.getStageType());
        stage.setDirector(entity.getDirector());
        stage.setOrder(entity.getStageOrder());

        return stage;
    }

    public List<RequirementTemplate> list() {
        List<RequirementTemplateEntity> templateEntities = templateDao.queryAllByLimit(0, 100);
        return templateEntities.stream().map(this::adapt).collect(Collectors.toList());
    }
}
