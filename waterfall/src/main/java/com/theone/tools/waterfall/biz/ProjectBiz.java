package com.theone.tools.waterfall.biz;

import com.theone.common.base.lang.BizException;
import com.theone.tools.waterfall.model.project.Project;
import com.theone.tools.waterfall.model.project.ProjectGroup;
import com.theone.tools.waterfall.service.ProjectService;
import com.theone.tools.waterfall.vo.ProjectGroupInfoResp;
import com.theone.tools.waterfall.vo.ProjectGroupListResp;
import com.theone.tools.waterfall.vo.ProjectInfoResp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chenxiaotong
 */
@Service
public class ProjectBiz {

    @Resource
    private ProjectService projectService;

    public void addGroup(String name) {
        projectService.groupAdd(name);
    }

    public ProjectGroupListResp listGroup(String username) {
        List<ProjectGroup> projectGroups = projectService.groupList();
        return new ProjectGroupListResp(projectGroups.size(), null);
    }

    public ProjectGroupInfoResp groupInfo(int id) {
        ProjectGroup projectGroup = projectService.groupInfo(id);
        ProjectGroupInfoResp resp = new ProjectGroupInfoResp();
        resp.setId(projectGroup.getId());
        resp.setName(projectGroup.getName());

        return resp;
    }

    public ProjectGroupListResp list(int groupId) {
        return null;
    }

    public ProjectInfoResp add(int groupId, String name, String desc, List<String> users) {
        Project project = new Project();
        project.setGroupId(groupId);
        project.setName(name);
        project.setDesc(desc);

        Project res = projectService.add(project, users);
        ProjectGroup group = projectService.groupInfo(res.getGroupId());
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
        ProjectGroup group = projectService.groupInfo(project.getGroupId());
        return adapt(project, group);
    }

    public ProjectGroupListResp groupList() {
        List<ProjectGroup> groupList = projectService.groupList();
        return new ProjectGroupListResp(groupList.size(), groupList.stream().map(this::adapt).collect(Collectors.toList()));
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
        projectService.groupDelete(id);
    }

    public ProjectGroupInfoResp groupUpdate(int id, String name) {
        ProjectGroup group = new ProjectGroup();
        group.setId(id);
        group.setName(name);

        projectService.groupUpdate(group);
        return adapt(projectService.groupInfo(id));
    }

    public void delete(int id) {
        projectService.delete(id);
    }
}
