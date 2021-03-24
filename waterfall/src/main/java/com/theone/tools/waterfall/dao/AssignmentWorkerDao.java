package com.theone.tools.waterfall.dao;

import com.theone.tools.waterfall.entity.AssignmentWorkerEntity;
import com.theone.tools.waterfall.model.assignment.AssignmentStatus;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * (AssignmentWorker)表数据库访问层
 *
 * @author makejava
 * @since 2021-03-17 13:55:49
 */
public interface AssignmentWorkerDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AssignmentWorkerEntity queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<AssignmentWorkerEntity> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param assignmentWorker 实例对象
     * @return 对象列表
     */
    List<AssignmentWorkerEntity> queryAll(AssignmentWorkerEntity assignmentWorker);

    /**
     * 新增数据
     *
     * @param assignmentWorker 实例对象
     * @return 影响行数
     */
    int insert(AssignmentWorkerEntity assignmentWorker);

    /**
     * 修改数据
     *
     * @param assignmentWorker 实例对象
     * @return 影响行数
     */
    int update(AssignmentWorkerEntity assignmentWorker);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    void insertList(List<AssignmentWorkerEntity> workers);

    void deleteByAssignment(Integer assignmentId);

    AssignmentWorkerEntity query(Integer assignmentId, String username);

    int compareAndUpdateStatus(@Param("assignmentId") Integer assignmentId, @Param("username") String username,
            @Param("before") AssignmentStatus before, @Param("after") AssignmentStatus after);

    void updateStatus(@Param("assignmentId") Integer assignmentId, @Param("username") String username,
            @Param("after") AssignmentStatus after);
}