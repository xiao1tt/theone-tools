package com.theone.tools.waterfall.vo;

import com.theone.tools.sso.client.UserGroup;
import com.theone.tools.waterfall.model.requirement.StageType;

/**
 * @author chenxiaotong
 */
public class TemplateStageSaveReq {

    private String name;

    private String desc;

    private StageType type;

    private UserGroup requiredGroup;

    private String director;

    private int order;

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
