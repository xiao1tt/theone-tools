package com.theone.tools.horde.dao;

import com.theone.tools.horde.entity.SessionEntity;

/**
 * (LoginSession)表数据库访问层
 *
 * @author makejava
 * @since 2021-02-26 19:15:56
 */
public interface SessionDao {
    void insertOrUpdate(SessionEntity entity);

    SessionEntity queryByUser(String username);

    SessionEntity queryByToken(String token);

    void deleteByToken(String token);

    void deleteByUser(String username);
}