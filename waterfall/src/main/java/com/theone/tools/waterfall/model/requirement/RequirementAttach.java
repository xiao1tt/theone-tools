package com.theone.tools.waterfall.model.requirement;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * (RequirementAttach)实体类
 *
 * @author makejava
 * @since 2021-03-14 19:32:37
 */
public class RequirementAttach implements Serializable {

    private static final long serialVersionUID = 468118672552457400L;

    private Integer id;

    private Integer requirementId;

    private String name;

    private String locate;

    private String attachType;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(Integer requirementId) {
        this.requirementId = requirementId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocate() {
        return locate;
    }

    public void setLocate(String locate) {
        this.locate = locate;
    }

    public String getAttachType() {
        return attachType;
    }

    public void setAttachType(String attachType) {
        this.attachType = attachType;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

}