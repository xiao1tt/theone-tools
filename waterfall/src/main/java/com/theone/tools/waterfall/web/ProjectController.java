package com.theone.tools.waterfall.web;

import com.theone.tools.waterfall.biz.ProjectBiz;
import com.theone.tools.waterfall.vo.*;
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
    public void groupAdd(@RequestBody ProjectGroupAddReq req) {
        projectBiz.addGroup(req.getName());
    }

    @GetMapping("/group/delete")
    public void groupDelete(int id) {
        projectBiz.groupDelete(id);
    }

    @PostMapping("/group/update")
    public ProjectGroupInfoResp groupUpdate(@RequestBody ProjectGroupUpdateReq req) {
        return projectBiz.groupUpdate(req.getId(), req.getName());
    }

    @GetMapping("/group/info")
    public ProjectGroupInfoResp groupInfo(int id) {
        return projectBiz.groupInfo(id);
    }

    @GetMapping("/group/list")
    public ProjectGroupListResp groupList() {
        return projectBiz.groupList();
    }

    @PostMapping("/add")
    public ProjectInfoResp add(@RequestBody ProjectAddReq req) {
        return projectBiz.add(req.getGroupId(), req.getName(), req.getDesc(), req.getUsers());
    }

    @GetMapping("/delete")
    public void delete(int id) {
        projectBiz.delete(id);
    }

    @PostMapping("/update")
    public ProjectInfoResp update(@RequestBody ProjectUpdateReq req) {
        return projectBiz.update(req.getId(), req.getName(), req.getDesc());
    }

    @GetMapping("/info")
    public ProjectInfoResp info(int id) {
        return projectBiz.info(id);
    }

    @GetMapping("/list")
    public ProjectGroupListResp list(int groupId) {
        return projectBiz.list(groupId);
    }
}
