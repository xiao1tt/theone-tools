package com.theone.tools.waterfall.service;

import com.theone.common.base.lang.BizException;
import com.theone.tools.waterfall.dao.ProjectDao;
import com.theone.tools.waterfall.dao.ProjectGroupDao;
import com.theone.tools.waterfall.dao.ProjectUserDao;
import com.theone.tools.waterfall.entity.ProjectEntity;
import com.theone.tools.waterfall.entity.ProjectGroupEntity;
import com.theone.tools.waterfall.model.project.Project;
import com.theone.tools.waterfall.model.project.ProjectGroup;
import com.theone.tools.waterfall.model.user.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Resource
    private UserService userService;
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

    public Project add(Project project, List<String> users) {
        ProjectEntity insert = adapt(project);
        int res = projectDao.insert(insert);
        if (res > 0) {
            initProjectUser(insert.getId(), users);
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

    private void initProjectUser(Integer projectId, List<String> users) {
        List<User> userList = userService.listByUsername(users);
        projectUserDao.insert(projectId, userList);
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
