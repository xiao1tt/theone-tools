package com.theone.tools.waterfall.dao;

import com.theone.tools.waterfall.entity.RequirementTemplateEntity;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (RequirementTemplate)表数据库访问层
 *
 * @author makejava
 * @since 2021-03-14 17:43:12
 */
public interface RequirementTemplateDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    RequirementTemplateEntity queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<RequirementTemplateEntity> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param requirementTemplate 实例对象
     * @return 对象列表
     */
    List<RequirementTemplateEntity> queryAll(RequirementTemplateEntity requirementTemplate);

    /**
     * 新增数据
     *
     * @param requirementTemplate 实例对象
     * @return 影响行数
     */
    int insert(RequirementTemplateEntity requirementTemplate);

    /**
     * 修改数据
     *
     * @param requirementTemplate 实例对象
     * @return 影响行数
     */
    int update(RequirementTemplateEntity requirementTemplate);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}