package com.theone.tools.horde.web;

import com.theone.tools.horde.bean.*;
import com.theone.tools.horde.biz.UserBiz;
import com.theone.tools.sso.client.UserGroup;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author chenxiaotong
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserBiz userBiz;

    @GetMapping("/list")
    public UserListView list(@RequestParam(required = false) String username,
                             @RequestParam(required = false) String realName,
                             @RequestParam(required = false) String phone,
                             @RequestParam(required = false) UserGroup group) {
        UserCondition condition = new UserCondition(username, realName, phone, group, null);
        return userBiz.list(condition);
    }

    @GetMapping("/info")
    public UserView query(String username) {
        return userBiz.query(username);
    }

    @PostMapping("/update")
    public UserView update(String username, @RequestBody UserUpdateReq req) {
        User user = new User();

        user.setUsername(username);
        user.setPhone(req.getPhone());
        user.setGroup(req.getGroup());
        user.setRealName(req.getRealName());
        user.setEmail(req.getEmail());
        user.setStatus(req.getStatus());

        return userBiz.update(user);
    }

    @PostMapping("/init")
    public UserView init(String phone, @RequestBody UserCompleteReq req) {
        User user = new User();

        user.setUsername(req.getUsername());
        user.setPhone(phone);
        user.setGroup(req.getGroup());
        user.setEmail(req.getEmail());

        return userBiz.init(user);
    }

    @GetMapping("/delete")
    public void delete(String username) {
        userBiz.delete(username);
    }

    @GetMapping("/sync")
    public UserListView sync() {
        return userBiz.sync();
    }
}
