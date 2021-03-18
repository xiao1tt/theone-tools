package com.theone.tools.waterfall.dao;

import com.theone.tools.waterfall.entity.ProjectUserEntity;
import com.theone.tools.waterfall.model.user.User;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * (ProjectUser)表数据库访问层
 *
 * @author makejava
 * @since 2021-03-14 12:38:19
 */
public interface ProjectUserDao {

    ProjectUserEntity query(@Param("projectId") Integer projectId, @Param("username") String username);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ProjectUserEntity> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param projectUser 实例对象
     * @return 对象列表
     */
    List<ProjectUserEntity> queryAll(ProjectUserEntity projectUser);

    int insertList(@Param("projectId") int projectId, @Param("users") List<ProjectUserEntity> users);

    int update(ProjectUserEntity entity);

    int deleteByProject(Integer projectId);

    int delete(@Param("projectId") Integer projectId, @Param("username") String username);

    void insert(ProjectUserEntity entity);
}