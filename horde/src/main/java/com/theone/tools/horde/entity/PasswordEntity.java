package com.theone.tools.horde.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * (Password)实体类
 *
 * @author makejava
 * @since 2021-02-25 13:58:39
 */
public class PasswordEntity implements Serializable {
    private static final long serialVersionUID = -29143809284495126L;
    
    private Integer id;
    
    private String username;
    
    private String password;
    
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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