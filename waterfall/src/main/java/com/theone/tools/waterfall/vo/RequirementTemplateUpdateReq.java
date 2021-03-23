package com.theone.tools.waterfall.vo;


import com.theone.tools.waterfall.model.requirement.TemplateStage;
import java.util.List;

/**
 * @author chenxiaotong
 */
public class RequirementTemplateUpdateReq {

    private String name;
    private String desc;
    private List<TemplateStage> stages;

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

    public List<TemplateStage> getStages() {
        return stages;
    }

    public void setStages(List<TemplateStage> stages) {
        this.stages = stages;
    }
}
