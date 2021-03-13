package com.theone.tools.waterfall.web;

import com.theone.tools.waterfall.vo.ProjectAddReq;
import com.theone.tools.waterfall.vo.ProjectGroupAddReq;
import com.theone.tools.waterfall.vo.ProjectGroupInfoResp;
import com.theone.tools.waterfall.vo.ProjectGroupListResp;
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
