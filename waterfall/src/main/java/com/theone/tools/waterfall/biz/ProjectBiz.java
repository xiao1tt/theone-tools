package com.theone.tools.waterfall.biz;

import com.theone.common.base.lang.BizException;
import com.theone.tools.waterfall.model.project.Project;
import com.theone.tools.waterfall.model.project.ProjectGroup;
import com.theone.tools.waterfall.model.project.ProjectUser;
import com.theone.tools.waterfall.model.user.Role;
import com.theone.tools.waterfall.service.ProjectGroupService;
import com.theone.tools.waterfall.service.ProjectService;
import com.theone.tools.waterfall.service.ProjectUserService;
import com.theone.tools.waterfall.vo.ProjectGroupInfoResp;
import com.theone.tools.waterfall.vo.ProjectGroupListResp;
import com.theone.tools.waterfall.vo.ProjectInfoResp;
import com.theone.tools.waterfall.vo.ProjectListItem;
import com.theone.tools.waterfall.vo.ProjectListResp;
import com.theone.tools.waterfall.vo.ProjectUserInfoResp;
import com.theone.tools.waterfall.vo.ProjectUserListResp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author chenxiaotong
 */
@Service
public class ProjectBiz {

    @Resource
    private ProjectService projectService;
    @Resource
    private ProjectGroupService projectGroupService;
    @Resource
    private ProjectUserService projectUserService;

    public void addGroup(String name) {
        projectGroupService.groupAdd(name);
    }

    public ProjectGroupListResp listGroup(String username) {
        List<ProjectGroup> projectGroups = projectGroupService.groupList();
        return new ProjectGroupListResp(projectGroups.size(), null);
    }

    public ProjectGroupInfoResp groupInfo(int id) {
        ProjectGroup projectGroup = projectGroupService.groupInfo(id);
        ProjectGroupInfoResp resp = new ProjectGroupInfoResp();
        resp.setId(projectGroup.getId());
        resp.setName(projectGroup.getName());

        return resp;
    }

    public ProjectListResp list() {
        List<ProjectGroup> projectGroups = projectGroupService.groupList();
        List<Project> list = projectService.list();
        Map<Integer, List<Project>> groupProjectMap = list.stream().collect(Collectors.groupingBy(Project::getGroupId));

        List<ProjectListItem> itemList = new ArrayList<>();
        for (ProjectGroup projectGroup : projectGroups) {
            List<Project> projects = groupProjectMap.get(projectGroup.getId());
            ProjectListItem item = new ProjectListItem();
            item.setGroupId(projectGroup.getId());
            item.setGroupName(projectGroup.getName());
            List<ProjectInfoResp> projectList = projects.stream()
                    .map(project -> adapt(project, projectGroup))
                    .collect(Collectors.toList());
            item.setProjectList(projectList);
            itemList.add(item);
        }

        ProjectListResp resp = new ProjectListResp();
        resp.setList(itemList);
        return resp;
    }

    public ProjectInfoResp add(int groupId, String name, String desc, List<String> users) {
        Project project = new Project();
        project.setGroupId(groupId);
        project.setName(name);
        project.setDesc(desc);

        Project res = projectService.add(project, users);
        ProjectGroup group = projectGroupService.groupInfo(res.getGroupId());
        return adapt(res, group);
    }

    private ProjectInfoResp adapt(Project project, ProjectGroup group) {
        if (project == null) {
            return null;
        }

        ProjectInfoResp resp = new ProjectInfoResp();
        resp.setId(project.getId());
        resp.setName(project.getName());
        resp.setDesc(project.getDesc());
        resp.setGroupId(group.getId());
        resp.setGroupName(group.getName());

        return resp;
    }

    public ProjectInfoResp update(int id, String name, String desc) {
        Project update = new Project();
        update.setId(id);
        update.setName(name);
        update.setDesc(desc);
        projectService.update(update);
        return this.info(id);
    }

    public ProjectInfoResp info(int id) {
        Project project = projectService.info(id);
        if (project == null) {
            throw new BizException("项目不存在，id:" + id);
        }
        ProjectGroup group = projectGroupService.groupInfo(project.getGroupId());
        return adapt(project, group);
    }

    public ProjectGroupListResp groupList() {
        List<ProjectGroup> groupList = projectGroupService.groupList();
        return new ProjectGroupListResp(groupList.size(),
                groupList.stream().map(this::adapt).collect(Collectors.toList()));
    }

    private ProjectGroupInfoResp adapt(ProjectGroup group) {
        if (group == null) {
            return null;
        }

        ProjectGroupInfoResp resp = new ProjectGroupInfoResp();
        resp.setId(group.getId());
        resp.setName(group.getName());

        return resp;
    }

    public void groupDelete(int id) {
        projectGroupService.groupDelete(id);
    }

    public ProjectGroupInfoResp groupUpdate(int id, String name) {
        ProjectGroup group = new ProjectGroup();
        group.setId(id);
        group.setName(name);

        projectGroupService.groupUpdate(group);
        return adapt(projectGroupService.groupInfo(id));
    }

    public void delete(int id) {
        projectService.delete(id);
    }

    public ProjectUserListResp userList(int projectId) {
        List<ProjectUser> projectUsers = projectUserService.userList(projectId);
        return new ProjectUserListResp(projectUsers.size(),
                projectUsers.stream().map(this::adapt).collect(Collectors.toList()));
    }

    private ProjectUserInfoResp adapt(ProjectUser user) {
        if (user == null) {
            return null;
        }

        ProjectUserInfoResp resp = new ProjectUserInfoResp();
        resp.setProjectId(user.getProjectId());
        resp.setUsername(user.getUsername());
        resp.setRole(user.getUserRole());

        return resp;
    }

    public ProjectUserListResp userAdd(int projectId, String username, Role role) {
        ProjectUser user = new ProjectUser();
        user.setProjectId(projectId);
        user.setUsername(username);
        user.setUserRole(role);

        projectUserService.userAdd(user);
        return this.userList(projectId);
    }

    public void userDelete(int projectId, String username) {
        projectUserService.userDelete(projectId, username);
    }

    public ProjectUserListResp userUpdate(int projectId, String username, Role role) {
        ProjectUser user = new ProjectUser();
        user.setProjectId(projectId);
        user.setUsername(username);
        user.setUserRole(role);

        projectUserService.userUpdate(user);
        return null;
    }

    public ProjectUserInfoResp userInfo(int projectId, String username) {
        ProjectUser user = projectUserService.userInfo(projectId, username);
        return adapt(user);
    }
}
