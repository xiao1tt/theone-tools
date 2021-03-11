package com.theone.tools.horde.service.impl;

import com.theone.tools.horde.entity.ImMappingEntity;
import com.theone.tools.horde.dao.ImMappingDao;
import com.theone.tools.horde.service.ImMappingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (ImMapping)表服务实现类
 *
 * @author makejava
 * @since 2021-02-25 13:58:57
 */
@Service
public class ImMappingServiceImpl implements ImMappingService {
    @Resource
    private ImMappingDao imMappingDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ImMappingEntity queryById(Integer id) {
        return this.imMappingDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<ImMappingEntity> queryAllByLimit(int offset, int limit) {
        return this.imMappingDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param imMapping 实例对象
     * @return 实例对象
     */
    @Override
    public ImMappingEntity insert(ImMappingEntity imMapping) {
        this.imMappingDao.insert(imMapping);
        return imMapping;
    }

    /**
     * 修改数据
     *
     * @param imMapping 实例对象
     * @return 实例对象
     */
    @Override
    public ImMappingEntity update(ImMappingEntity imMapping) {
        this.imMappingDao.update(imMapping);
        return this.queryById(imMapping.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.imMappingDao.deleteById(id) > 0;
    }
}