package com.theone.tools.horde.dao;

import com.theone.tools.horde.bean.UserCondition;
import com.theone.tools.horde.entity.UserEntity;

import java.util.List;

/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2021-02-25 13:54:14
 */
public interface UserDao {

    UserEntity query(String username);

    List<UserEntity> list(UserCondition condition);

    int insert(UserEntity user);

    int update(UserEntity user);

    int delete(String username);

    UserEntity queryByPhone(String phone);

    int updateByPhone(UserEntity user);
}