package com.theone.tools.waterfall.model.requirement;

import java.io.Serializable;
import java.util.List;

/**
 * @author chenxiaotong
 */
public class RequirementTemplate implements Serializable {

    private int id;
    private String name;
    private String desc;
    private List<TemplateStage> stages;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
