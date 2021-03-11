package com.theone.tools.horde.service;

import com.theone.tools.horde.bean.User;
import com.theone.tools.horde.bean.UserCondition;

import java.util.List;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2021-02-25 13:54:17
 */
public interface UserService {

    User query(String username);

    List<User> list(UserCondition condition);

    void insert(User user);

    User update(User user);

    boolean available(String username);

    void delete(String username);

    User queryByPhone(String mobile);

    User updateByPhone(User user);
}