package com.theone.tools.waterfall.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author chenxiaotong
 */
public class RequirementTemplateInfoResp implements Serializable {
    private int id;
    private String name;
    private String desc;
    private List<TemplateStageInfoResp> stages;

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

    public List<TemplateStageInfoResp> getStages() {
        return stages;
    }

    public void setStages(List<TemplateStageInfoResp> stages) {
        this.stages = stages;
    }
}
