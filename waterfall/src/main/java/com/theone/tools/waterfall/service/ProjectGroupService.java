package com.theone.tools.waterfall.service;

import com.theone.common.base.lang.BizException;
import com.theone.tools.waterfall.dao.ProjectDao;
import com.theone.tools.waterfall.dao.ProjectGroupDao;
import com.theone.tools.waterfall.dao.ProjectUserDao;
import com.theone.tools.waterfall.entity.ProjectEntity;
import com.theone.tools.waterfall.entity.ProjectGroupEntity;
import com.theone.tools.waterfall.model.project.ProjectGroup;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author chenxiaotong
 */
@Service
public class ProjectGroupService {

    @Resource
    private ProjectDao projectDao;
    @Resource
    private ProjectUserDao projectUserDao;
    @Resource
    private ProjectGroupDao projectGroupDao;

    public void groupAdd(String name) {
        ProjectGroupEntity e = new ProjectGroupEntity();
        e.setName(name);

        projectGroupDao.insert(e);
    }

    public List<ProjectGroup> groupList() {
        return projectGroupDao.list().stream().map(this::adapt).collect(Collectors.toList());
    }

    public ProjectGroup groupInfo(int id) {
        ProjectGroupEntity entity = projectGroupDao.queryById(id);
        return adapt(entity);
    }

    private ProjectGroup adapt(ProjectGroupEntity entity) {
        if (entity == null) {
            return null;
        }

        ProjectGroup group = new ProjectGroup();
        group.setId(entity.getId());
        group.setName(entity.getName());
        return group;
    }

    public void groupDelete(int id) {
        ProjectEntity query = new ProjectEntity();
        query.setGroupId(id);

        List<ProjectEntity> projectEntities = projectDao.queryAll(query);

        if (!projectEntities.isEmpty()) {
            throw new BizException("项目组不为空，不可删除");
        }

        projectGroupDao.deleteById(id);
    }

    public void groupUpdate(ProjectGroup group) {
        projectGroupDao.update(adapt(group));
    }

    private ProjectGroupEntity adapt(ProjectGroup group) {
        if (group == null) {
            return null;
        }

        ProjectGroupEntity entity = new ProjectGroupEntity();
        entity.setId(0);
        entity.setName(group.getName());

        return entity;
    }

    public void delete(int id) {
        projectDao.deleteById(id);
        projectUserDao.deleteByProject(id);
    }
}
