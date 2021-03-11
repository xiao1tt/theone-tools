package com.theone.tools.waterfall.model.requirement;

import com.theone.tools.sso.client.UserGroup;

/**
 * @author chenxiaotong
 */
public class TemplateStage {

    private String name;

    private String desc;

    private UserGroup requiredGroup;

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
}
