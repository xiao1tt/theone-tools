package com.theone.tools.waterfall.vo;

import com.theone.tools.sso.client.UserGroup;

/**
 * @author chenxiaotong
 */
public class TemplateStageInfoResp {
    private int id;

    private int templateId;

    private String name;

    private String desc;

    private UserGroup requiredGroup;

    private String requiredGroupView;

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

    public String getRequiredGroupView() {
        return requiredGroupView;
    }

    public void setRequiredGroupView(String requiredGroupView) {
        this.requiredGroupView = requiredGroupView;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
