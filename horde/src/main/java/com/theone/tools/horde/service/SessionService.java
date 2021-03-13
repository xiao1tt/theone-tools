package com.theone.tools.horde.service;

import com.theone.tools.horde.bean.Session;

/**
 * (LoginSession)表服务接口
 *
 * @author makejava
 * @since 2021-02-26 19:15:56
 */
public interface SessionService {

    Session queryByUsername(String username);

    Session queryByToken(String token);

    void clearByToken(String token);

    void clearByUser(String username);

    void save(String username, String token);
}