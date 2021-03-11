package com.theone.tools.horde.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * (ImMapping)实体类
 *
 * @author makejava
 * @since 2021-02-25 13:58:57
 */
public class ImMappingEntity implements Serializable {
    private static final long serialVersionUID = 963531566829936334L;
    
    private Integer id;
    
    private String username;
    
    private String imId;
    
    private String status;
    
    private LocalDateTime createTime;
    
    private LocalDateTime updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImId() {
        return imId;
    }

    public void setImId(String imId) {
        this.imId = imId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

}