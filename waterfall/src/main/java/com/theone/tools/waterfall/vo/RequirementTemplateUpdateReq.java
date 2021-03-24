package com.theone.tools.waterfall.vo;


import com.theone.tools.waterfall.model.requirement.TemplateStage;
import java.util.List;

/**
 * @author chenxiaotong
 */
public class RequirementTemplateUpdateReq {

    private String name;
    private String desc;
    private List<TemplateStageSaveReq> stages;

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

    public List<TemplateStageSaveReq> getStages() {
        return stages;
    }

    public void setStages(List<TemplateStageSaveReq> stages) {
        this.stages = stages;
    }
}
