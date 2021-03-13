package com.theone.tools.horde.bean;

import java.time.LocalDateTime;

/**
 * @author chenxiaotong
 */
public class Session {
    private String username;

    private String token;

    private LocalDateTime createTime;

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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
