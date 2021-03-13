package com.theone.tools.waterfall.biz;

import com.theone.tools.waterfall.model.project.ProjectGroup;
import com.theone.tools.waterfall.vo.ProjectGroupInfoResp;
import com.theone.tools.waterfall.vo.ProjectGroupListResp;
import com.theone.tools.waterfall.service.ProjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProjectBiz {

    @Resource
    private ProjectService projectService;

    public void addGroup(String name) {
        projectService.addGroup(name);
    }

    public ProjectGroupListResp listGroup(String username) {
        List<ProjectGroup> projectGroups = projectService.listGroup();
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

    public void add(String name, String desc, List<String> users) {

    }
}
