package com.theone.tools.horde.service;

import com.theone.tools.horde.entity.ImMappingEntity;
import java.util.List;

/**
 * (ImMapping)表服务接口
 *
 * @author makejava
 * @since 2021-02-25 13:58:57
 */
public interface ImMappingService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ImMappingEntity queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ImMappingEntity> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param imMapping 实例对象
     * @return 实例对象
     */
    ImMappingEntity insert(ImMappingEntity imMapping);

    /**
     * 修改数据
     *
     * @param imMapping 实例对象
     * @return 实例对象
     */
    ImMappingEntity update(ImMappingEntity imMapping);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}