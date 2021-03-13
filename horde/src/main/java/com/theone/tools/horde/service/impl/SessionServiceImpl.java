package com.theone.tools.horde.service.impl;

import com.theone.tools.horde.bean.Session;
import com.theone.tools.horde.dao.SessionDao;
import com.theone.tools.horde.entity.SessionEntity;
import com.theone.tools.horde.service.SessionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (LoginSession)表服务实现类
 *
 * @author makejava
 * @since 2021-02-26 19:15:56
 */
@Service
public class SessionServiceImpl implements SessionService {
    @Resource
    private SessionDao sessionDao;

    @Override
    public Session queryByUsername(String username) {
        SessionEntity entity = sessionDao.queryByUser(username);
        return adapt(entity);
    }

    private Session adapt(SessionEntity entity) {
        if (entity == null) {
            return null;
        }

        Session session = new Session();
        session.setUsername(entity.getUsername());
        session.setToken(entity.getToken());
        session.setCreateTime(entity.getCreateTime());
        return session;
    }

    @Override
    public Session queryByToken(String token) {
        SessionEntity entity = sessionDao.queryByToken(token);
        return adapt(entity);
    }

    @Override
    public void clearByToken(String token) {
        sessionDao.deleteByToken(token);
    }

    @Override
    public void clearByUser(String username) {
        sessionDao.deleteByUser(username);
    }

    @Override
    public void save(String username, String token) {
        SessionEntity entity = new SessionEntity(username, token);
        sessionDao.insertOrUpdate(entity);
    }
}