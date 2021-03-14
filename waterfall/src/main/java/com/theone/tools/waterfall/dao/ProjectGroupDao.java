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

    List<ProjectGroupEntity> list();

    int insert(ProjectGroupEntity projectGroup);

    int update(ProjectGroupEntity projectGroup);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}