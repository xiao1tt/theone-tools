package com.theone.tools.horde.dao;

import com.theone.tools.horde.entity.PasswordEntity;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Password)表数据库访问层
 *
 * @author makejava
 * @since 2021-02-25 13:58:39
 */
public interface PasswordDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PasswordEntity queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<PasswordEntity> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param password 实例对象
     * @return 对象列表
     */
    List<PasswordEntity> queryAll(PasswordEntity password);

    /**
     * 新增数据
     *
     * @param password 实例对象
     * @return 影响行数
     */
    int insert(PasswordEntity password);

    /**
     * 修改数据
     *
     * @param password 实例对象
     * @return 影响行数
     */
    int update(PasswordEntity password);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    PasswordEntity query(String username);
}