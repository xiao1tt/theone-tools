package com.theone.tools.waterfall.web;

import com.theone.tools.waterfall.biz.ProjectBiz;
import com.theone.tools.waterfall.vo.ProjectAddReq;
import com.theone.tools.waterfall.vo.ProjectGroupAddReq;
import com.theone.tools.waterfall.vo.ProjectGroupInfoResp;
import com.theone.tools.waterfall.vo.ProjectGroupListResp;
import com.theone.tools.waterfall.vo.ProjectGroupUpdateReq;
import com.theone.tools.waterfall.vo.ProjectInfoResp;
import com.theone.tools.waterfall.vo.ProjectListResp;
import com.theone.tools.waterfall.vo.ProjectUpdateReq;
import com.theone.tools.waterfall.vo.ProjectUserAddReq;
import com.theone.tools.waterfall.vo.ProjectUserInfoResp;
import com.theone.tools.waterfall.vo.ProjectUserListResp;
import com.theone.tools.waterfall.vo.ProjectUserUpdateReq;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目管理
 *
 * @author chenxiaotong
 */
@RestController
@RequestMapping("/project")
public class ProjectController {

    @Resource
    private ProjectBiz projectBiz;

    /**
     * 增加项目组
     */
    @PostMapping("/group/add")
    public void groupAdd(@RequestBody ProjectGroupAddReq req) {
        projectBiz.addGroup(req.getName());
    }

    /**
     * 删除项目组
     */
    @GetMapping("/group/delete")
    public void groupDelete(int id) {
        projectBiz.groupDelete(id);
    }

    /**
     * 更新项目组
     */
    @PostMapping("/group/update")
    public ProjectGroupInfoResp groupUpdate(@RequestBody ProjectGroupUpdateReq req) {
        return projectBiz.groupUpdate(req.getId(), req.getName());
    }

    /**
     * 查询项目组信息
     */
    @GetMapping("/group/info")
    public ProjectGroupInfoResp groupInfo(int id) {
        return projectBiz.groupInfo(id);
    }

    /**
     * 查询项目组列表
     */
    @GetMapping("/group/list")
    public ProjectGroupListResp groupList() {
        return projectBiz.groupList();
    }

    /**
     * 新建项目
     */
    @PostMapping("/add")
    public ProjectInfoResp add(@RequestBody ProjectAddReq req) {
        return projectBiz.add(req.getGroupId(), req.getName(), req.getDesc(), req.getUsers());
    }

    /**
     * 删除项目
     */
    @PostMapping("/delete")
    public void delete(int id) {
        projectBiz.delete(id);
    }

    /**
     * 更新项目
     */
    @PostMapping("/update")
    public ProjectInfoResp update(@RequestBody ProjectUpdateReq req) {
        return projectBiz.update(req.getId(), req.getName(), req.getDesc());
    }

    /**
     * 查询项目详情
     */
    @GetMapping("/info")
    public ProjectInfoResp info(int id) {
        return projectBiz.info(id);
    }

    /**
     * 查询项目列表
     */
    @GetMapping("/list")
    public ProjectListResp list() {
        return projectBiz.list();
    }

    /**
     * 项目中增加用户
     */
    @PostMapping("/user/add")
    public ProjectUserListResp userAdd(int projectId, @RequestBody ProjectUserAddReq req) {
        return projectBiz.userAdd(projectId, req.getUsername(), req.getRole());
    }

    /**
     * 项目中删除用户
     */
    @GetMapping("/user/delete")
    public void userDelete(int projectId, String username) {
        projectBiz.userDelete(projectId, username);
    }

    /**
     * 更新项目用户（更新用户角色）
     */
    @PostMapping("/user/update")
    public ProjectUserListResp userUpdate(int projectId, String username, @RequestBody ProjectUserUpdateReq req) {
        return projectBiz.userUpdate(projectId, username, req.getRole());
    }

    /**
     * 查询项目用户详情
     */
    @GetMapping("/user/info")
    public ProjectUserInfoResp userInfo(int projectId, String username) {
        return projectBiz.userInfo(projectId, username);
    }

    /**
     * 查询项目下用户列表
     */
    @GetMapping("/user/list")
    public ProjectUserListResp userList(int projectId) {
        return projectBiz.userList(projectId);
    }
}
