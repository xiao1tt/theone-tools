package com.theone.tools.waterfall.dao;

import com.theone.tools.waterfall.entity.ProjectGroupEntity;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (ProjectGroup)表数据库访问层
 *
 * @author makejava
 * @since 2021-03-11 19:52:28
 */
public interface ProjectGroupDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ProjectGroupEntity queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ProjectGroupEntity> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param projectGroup 实例对象
     * @return 对象列表
     */
    List<ProjectGroupEntity> queryAll(ProjectGroupEntity projectGroup);

    /**
     * 新增数据
     *
     * @param projectGroup 实例对象
     * @return 影响行数
     */
    int insert(ProjectGroupEntity projectGroup);

    /**
     * 修改数据
     *
     * @param projectGroup 实例对象
     * @return 影响行数
     */
    int update(ProjectGroupEntity projectGroup);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}