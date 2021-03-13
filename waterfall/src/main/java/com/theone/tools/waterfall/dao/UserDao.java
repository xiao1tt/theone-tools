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


    /**
     * 通过实体作为筛选条件查询
     *
     * @param user 实例对象
     * @return 对象列表
     */
    List<UserEntity> list(UserEntity user);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int insert(UserEntity user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int update(UserEntity user);
}