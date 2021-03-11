package com.theone.tools.horde.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * (LoginSession)实体类
 *
 * @author makejava
 * @since 2021-02-26 19:15:56
 */
public class SessionEntity implements Serializable {
    private static final long serialVersionUID = 242262492883523215L;
    
    private Integer id;
    
    private String username;
    
    private String token;
    
    private LocalDateTime updateTime;
    
    private LocalDateTime createTime;

    public SessionEntity(String username, String token) {
        this.username = username;
        this.token = token;
    }

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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