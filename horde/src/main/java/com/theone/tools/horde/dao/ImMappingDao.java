package com.theone.tools.horde.dao;

import com.theone.tools.horde.entity.ImMappingEntity;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (ImMapping)表数据库访问层
 *
 * @author makejava
 * @since 2021-02-25 13:58:57
 */
public interface ImMappingDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ImMappingEntity queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ImMappingEntity> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param imMapping 实例对象
     * @return 对象列表
     */
    List<ImMappingEntity> queryAll(ImMappingEntity imMapping);

    /**
     * 新增数据
     *
     * @param imMapping 实例对象
     * @return 影响行数
     */
    int insert(ImMappingEntity imMapping);

    /**
     * 修改数据
     *
     * @param imMapping 实例对象
     * @return 影响行数
     */
    int update(ImMappingEntity imMapping);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}