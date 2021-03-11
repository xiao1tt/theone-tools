package com.theone.tools.waterfall.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * (ProjectGroup)实体类
 *
 * @author makejava
 * @since 2021-03-11 19:52:28
 */
public class ProjectGroupEntity implements Serializable {
    private static final long serialVersionUID = -15681770246957181L;
    
    private Integer id;
    
    private String name;
    
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