package com.theone.tools.waterfall.service;

import com.theone.tools.waterfall.dao.ProjectDao;
import com.theone.tools.waterfall.entity.ProjectEntity;
import com.theone.tools.waterfall.model.project.Project;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author chenxiaotong
 */
@Service
public class ProjectService {

    @Resource
    private ProjectUserService projectUserService;
    @Resource
    private ProjectDao projectDao;

    public Project add(Project project, List<String> users) {
        ProjectEntity insert = adapt(project);
        int res = projectDao.insert(insert);
        if (res > 0) {
            projectUserService.initProjectUser(insert.getId(), users);
        }

        return adapt(insert);
    }

    private Project adapt(ProjectEntity entity) {
        if (entity == null) {
            return null;
        }
        Project project = new Project();
        project.setId(entity.getId());
        project.setGroupId(entity.getGroupId());
        project.setName(entity.getName());
        project.setDesc(entity.getProjectDesc());

        return project;
    }

    private ProjectEntity adapt(Project project) {
        ProjectEntity entity = new ProjectEntity();
        entity.setGroupId(project.getGroupId());
        entity.setName(project.getName());
        entity.setProjectDesc(project.getDesc());

        return entity;
    }

    public void update(Project project) {
        projectDao.update(adapt(project));
    }

    public Project info(int id) {
        return adapt(projectDao.queryById(id));
    }

    public void delete(int id) {
        projectDao.deleteById(id);
        projectUserService.deleteAll(id);
    }

    public List<Project> listByIds(Collection<Integer> ids) {
        List<ProjectEntity> projectEntities = projectDao.queryByIds(ids);
        return projectEntities.stream().map(this::adapt).collect(Collectors.toList());
    }
}
