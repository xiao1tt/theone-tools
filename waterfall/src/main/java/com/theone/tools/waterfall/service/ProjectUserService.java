package com.theone.tools.waterfall.service;

import com.theone.tools.waterfall.dao.ProjectUserDao;
import com.theone.tools.waterfall.entity.ProjectUserEntity;
import com.theone.tools.waterfall.model.project.ProjectUser;
import com.theone.tools.waterfall.model.user.User;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.apache.commons.compress.utils.Lists;
import org.springframework.stereotype.Service;

/**
 * @author chenxiaotong
 */
@Service
public class ProjectUserService {

    @Resource
    private GlobalUserService userService;
    @Resource
    private ProjectUserDao projectUserDao;

    public void initProjectUser(Integer projectId, List<String> users) {
        List<User> userList = userService.listByUsername(users);
        List<ProjectUserEntity> insertList = Lists.newArrayList();
        for (User user : userList) {
            ProjectUserEntity insert = new ProjectUserEntity();
            insert.setProjectId(projectId);
            insert.setUsername(user.getUsername());
            insert.setUserRole(user.getUserRole());
            insertList.add(insert);
        }
        projectUserDao.insertList(projectId, insertList);
    }

    public List<ProjectUser> userList(int projectId) {
        ProjectUserEntity query = new ProjectUserEntity();
        query.setProjectId(projectId);

        return projectUserDao.queryAll(query).stream().map(this::adapt).collect(Collectors.toList());
    }

    private ProjectUser adapt(ProjectUserEntity entity) {
        if (entity == null) {
            return null;
        }

        ProjectUser user = new ProjectUser();
        user.setProjectId(entity.getProjectId());
        user.setUsername(entity.getUsername());
        user.setUserRole(entity.getUserRole());

        return user;
    }

    public void userDelete(int projectId, String username) {
        projectUserDao.delete(projectId, username);
    }

    public void userUpdate(ProjectUser user) {
        projectUserDao.update(adapt(user));
    }

    private ProjectUserEntity adapt(ProjectUser user) {
        if (user == null) {
            return null;
        }

        ProjectUserEntity entity = new ProjectUserEntity();
        entity.setProjectId(user.getProjectId());
        entity.setUsername(user.getUsername());
        entity.setUserRole(user.getUserRole());

        return entity;
    }

    public ProjectUser userInfo(int projectId, String username) {
        return adapt(projectUserDao.query(projectId, username));
    }

    public void userAdd(ProjectUser user) {
        projectUserDao.insert(adapt(user));
    }

    public List<ProjectUser> projectByUser(String username) {
        ProjectUserEntity query = new ProjectUserEntity();
        query.setUsername(username);

        List<ProjectUserEntity> projectUsers = projectUserDao.queryAll(query);
        return projectUsers.stream().map(this::adapt).collect(Collectors.toList());
    }

    public void deleteAll(int projectId) {
        projectUserDao.deleteByProject(projectId);
    }
}
