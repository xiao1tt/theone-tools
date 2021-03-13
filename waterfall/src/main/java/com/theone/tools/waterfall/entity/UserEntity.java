package com.theone.tools.waterfall.entity;

import com.theone.tools.waterfall.model.user.Role;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2021-03-13 13:53:11
 */
public class UserEntity implements Serializable {
    private static final long serialVersionUID = -96499047760554020L;
    
    private Integer id;
    
    private String username;
    
    private Role userRole;
    
    private LocalDateTime updateTime;
    
    private LocalDateTime createTime;

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

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
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