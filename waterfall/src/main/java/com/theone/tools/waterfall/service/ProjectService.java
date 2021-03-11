package com.theone.tools.waterfall.service;

import com.theone.tools.waterfall.dao.ProjectDao;
import com.theone.tools.waterfall.dao.ProjectGroupDao;
import com.theone.tools.waterfall.entity.ProjectGroupEntity;
import com.theone.tools.waterfall.model.project.ProjectGroup;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProjectService {

    @Resource
    private ProjectDao projectDao;
    @Resource
    private ProjectGroupDao projectGroupDao;

    public void addGroup(String name) {
        ProjectGroupEntity e = new ProjectGroupEntity();
        e.setName(name);

        projectGroupDao.insert(e);
    }

    public List<ProjectGroup> listGroup() {
        return null;
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
}
