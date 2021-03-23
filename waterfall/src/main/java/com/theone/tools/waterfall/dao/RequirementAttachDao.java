package com.theone.tools.waterfall.dao;

import com.theone.tools.waterfall.entity.RequirementAttachEntity;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * (RequirementAttach)表数据库访问层
 *
 * @author makejava
 * @since 2021-03-14 19:32:37
 */
public interface RequirementAttachDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    RequirementAttachEntity queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<RequirementAttachEntity> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param requirementAttach 实例对象
     * @return 对象列表
     */
    List<RequirementAttachEntity> queryAll(RequirementAttachEntity requirementAttach);

    /**
     * 新增数据
     *
     * @param requirementAttach 实例对象
     * @return 影响行数
     */
    int insert(RequirementAttachEntity requirementAttach);

    /**
     * 修改数据
     *
     * @param requirementAttach 实例对象
     * @return 影响行数
     */
    int update(RequirementAttachEntity requirementAttach);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    void deleteByRequirement(int id);
}