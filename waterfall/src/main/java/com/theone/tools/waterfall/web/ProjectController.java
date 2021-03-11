package com.theone.tools.waterfall.web;

import com.theone.tools.sso.client.IUserContext;
import com.theone.tools.waterfall.transfer.ProjectAddReq;
import com.theone.tools.waterfall.transfer.ProjectGroupAddReq;
import com.theone.tools.waterfall.transfer.ProjectGroupInfoResp;
import com.theone.tools.waterfall.transfer.ProjectGroupListResp;
import com.theone.tools.waterfall.biz.ProjectBiz;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author chenxiaotong
 */
@RestController
@RequestMapping("/project")
public class ProjectController {

    @Resource
    private ProjectBiz projectBiz;

    @PostMapping("/group/add")
    public void addGroup(@RequestBody ProjectGroupAddReq req) {
        projectBiz.addGroup(req.getName());
    }

    @GetMapping("/group/info")
    public ProjectGroupInfoResp groupInfo(int id) {
        return projectBiz.groupInfo(id);
    }

    @GetMapping("/list")
    public ProjectGroupListResp list(int groupId) {
        return projectBiz.list(groupId);
    }

    @RequestMapping("/add")
    public void add(@RequestBody ProjectAddReq req) {
        projectBiz.add(req.getName(), req.getDesc(), req.getUsers());
    }

}
