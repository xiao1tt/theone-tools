package com.theone.tools.waterfall.service;

import com.theone.tools.waterfall.dao.RequirementTemplateDao;
import com.theone.tools.waterfall.dao.RequirementTemplateStageDao;
import com.theone.tools.waterfall.entity.RequirementTemplateEntity;
import com.theone.tools.waterfall.entity.RequirementTemplateStageEntity;
import com.theone.tools.waterfall.model.requirement.RequirementTemplate;
import com.theone.tools.waterfall.model.requirement.RequirementTemplateStruct;
import com.theone.tools.waterfall.model.requirement.TemplateStage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chenxiaotong
 */
@Service
public class RequirementTemplateService {

    @Resource
    private RequirementTemplateDao templateDao;
    @Resource
    private RequirementTemplateStageDao templateStageDao;

    @Transactional(rollbackFor = Throwable.class)
    public void add(RequirementTemplate template, List<TemplateStage> stageList) {
        RequirementTemplateEntity insert = adapt(template);
        templateDao.insert(insert);
        templateStageDao.insertAll(insert.getId(), stageList.stream().map(this::adapt).collect(Collectors.toList()));
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
        entity.setStageType(stage.getType());
        entity.setDirector(stage.getDirector());

        return entity;
    }

    @Transactional(rollbackFor = Throwable.class)
    public void delete(int templateId) {
        templateDao.deleteById(templateId);
        templateStageDao.deleteByTemplate(templateId);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void update(RequirementTemplate template, List<TemplateStage> stageList) {
        templateDao.update(adapt(template));
        templateStageDao.deleteByTemplate(template.getId());
        templateStageDao.insertAll(template.getId(), stageList.stream().map(this::adapt).collect(Collectors.toList()));
    }

    public RequirementTemplate info(int id) {
        RequirementTemplateEntity entity = templateDao.queryById(id);
        return adapt(entity);
    }

    public RequirementTemplateStruct struct(int id) {
        RequirementTemplate template = this.info(id);

        List<TemplateStage> templateStages = this.stageList(id);
        return assemble(template, templateStages);
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

    private RequirementTemplateStruct assemble(RequirementTemplate template, List<TemplateStage> stages) {
        if (template == null) {
            return null;
        }

        RequirementTemplateStruct struct = new RequirementTemplateStruct();
        struct.setTemplate(template);
        struct.setStages(stages);

        return struct;
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
        List<RequirementTemplateEntity> templateEntities = templateDao.queryAll(new RequirementTemplateEntity());
        return templateEntities.stream().map(this::adapt).collect(Collectors.toList());
    }

    public List<TemplateStage> stageList(int templateId) {
        RequirementTemplateStageEntity query = new RequirementTemplateStageEntity();
        query.setTemplateId(templateId);

        List<RequirementTemplateStageEntity> stageEntities = templateStageDao.queryAll(query);
        return stageEntities.stream().map(this::adapt).collect(Collectors.toList());
    }

    public List<RequirementTemplateStruct> listStruct() {
        List<RequirementTemplateEntity> templateEntities = templateDao.queryAll(new RequirementTemplateEntity());
        List<RequirementTemplate> templates = templateEntities.stream().map(this::adapt).collect(Collectors.toList());
        List<RequirementTemplateStageEntity> stageEntities = templateStageDao
                .queryAll(new RequirementTemplateStageEntity());
        Map<Integer, List<TemplateStage>> templateStageMap = stageEntities.stream().map(this::adapt)
                .collect(Collectors.groupingBy(TemplateStage::getTemplateId));

        List<RequirementTemplateStruct> result = new ArrayList<>();
        for (RequirementTemplate template : templates) {
            RequirementTemplateStruct struct = new RequirementTemplateStruct();
            struct.setTemplate(template);
            struct.setStages(templateStageMap.get(template.getId()));
            result.add(struct);
        }
        return result;
    }
}
