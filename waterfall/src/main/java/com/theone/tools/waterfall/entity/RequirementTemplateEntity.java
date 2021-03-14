package com.theone.tools.waterfall.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * (RequirementTemplate)实体类
 *
 * @author makejava
 * @since 2021-03-14 17:43:12
 */
public class RequirementTemplateEntity implements Serializable {
    private static final long serialVersionUID = -53569023524059716L;
    
    private Integer id;
    
    private String name;
    
    private String templateDesc;
    
    private LocalDateTime updateTime;
    
    private LocalDateTime createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemplateDesc() {
        return templateDesc;
    }

    public void setTemplateDesc(String templateDesc) {
        this.templateDesc = templateDesc;
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