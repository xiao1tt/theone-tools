package com.theone.tools.waterfall.model.requirement;

import com.theone.tools.sso.client.UserGroup;

/**
 * @author chenxiaotong
 */
public class TemplateStage {
    private int id;

    private int templateId;

    private String name;

    private String desc;

    private StageType type;

    private UserGroup requiredGroup;

    private String director;

    private int order;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
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

    public UserGroup getRequiredGroup() {
        return requiredGroup;
    }

    public void setRequiredGroup(UserGroup requiredGroup) {
        this.requiredGroup = requiredGroup;
    }

    public StageType getType() {
        return type;
    }

    public void setType(StageType type) {
        this.type = type;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
