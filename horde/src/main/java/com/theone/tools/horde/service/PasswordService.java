package com.theone.tools.horde.service;

import com.theone.tools.horde.entity.PasswordEntity;
import java.util.List;

/**
 * (Password)表服务接口
 *
 * @author makejava
 * @since 2021-02-25 13:58:39
 */
public interface PasswordService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PasswordEntity queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<PasswordEntity> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param password 实例对象
     * @return 实例对象
     */
    PasswordEntity insert(PasswordEntity password);

    /**
     * 修改数据
     *
     * @param password 实例对象
     * @return 实例对象
     */
    PasswordEntity update(PasswordEntity password);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    boolean check(String username, String password);
}