package com.theone.tools.waterfall.dao;

import com.theone.tools.waterfall.entity.UserEntity;

import java.util.List;

/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2021-03-13 13:53:11
 */
public interface UserDao {

    UserEntity query(String username);

    List<UserEntity> all();

    List<UserEntity> list(UserEntity user);

    int insert(UserEntity user);

    int update(UserEntity user);

    List<UserEntity> listByUsername(List<String> users);
}