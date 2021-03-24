package com.theone.tools.waterfall.model.requirement;

import java.io.Serializable;
import java.util.List;

/**
 * @author chenxiaotong
 */
public class RequirementTemplateStruct implements Serializable {

    private RequirementTemplate template;
    private List<TemplateStage> stages;

    public RequirementTemplate getTemplate() {
        return template;
    }

    public void setTemplate(RequirementTemplate template) {
        this.template = template;
    }

    public List<TemplateStage> getStages() {
        return stages;
    }

    public void setStages(List<TemplateStage> stages) {
        this.stages = stages;
    }
}
