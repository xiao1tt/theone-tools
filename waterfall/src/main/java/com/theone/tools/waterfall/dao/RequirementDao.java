package com.theone.tools.waterfall.dao;

import com.theone.tools.waterfall.entity.RequirementEntity;
import com.theone.tools.waterfall.model.requirement.RequirementStatus;
import java.util.Collection;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * (Requirement)表数据库访问层
 *
 * @author makejava
 * @since 2021-03-14 18:36:30
 */
public interface RequirementDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    RequirementEntity queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<RequirementEntity> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param requirement 实例对象
     * @return 对象列表
     */
    List<RequirementEntity> queryAll(RequirementEntity requirement);

    /**
     * 新增数据
     *
     * @param requirement 实例对象
     * @return 影响行数
     */
    int insert(RequirementEntity requirement);

    /**
     * 修改数据
     *
     * @param requirement 实例对象
     * @return 影响行数
     */
    int update(RequirementEntity requirement);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<RequirementEntity> queryByIds(@Param("ids") Collection<Integer> ids,
            @Param("status") RequirementStatus status);
}