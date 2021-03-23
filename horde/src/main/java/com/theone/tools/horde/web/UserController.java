package com.theone.tools.horde.web;

import com.theone.common.base.lang.BizException;
import com.theone.tools.horde.bean.User;
import com.theone.tools.horde.bean.UserCompleteReq;
import com.theone.tools.horde.bean.UserCondition;
import com.theone.tools.horde.bean.UserListView;
import com.theone.tools.horde.bean.UserUpdateReq;
import com.theone.tools.horde.bean.UserView;
import com.theone.tools.horde.biz.UserBiz;
import com.theone.tools.sso.client.IUserContext;
import com.theone.tools.sso.client.UserGroup;
import com.theone.tools.sso.client.UserLevel;
import com.theone.tools.sso.client.UserStatus;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理
 *
 * @author chenxiaotong
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserBiz userBiz;

    /**
     * 用户列表
     *
     * @param username 查询参数，用户名
     * @param realName 查询参数，真实姓名
     * @param phone 查询参数，手机号
     * @param group 查询参数，用户分组
     * @param level 查询参数，用户级别
     * @param status 查询参数，用户状态
     */
    @GetMapping("/list")
    public UserListView list(@RequestParam(required = false) String username,
            @RequestParam(required = false) String realName,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) UserGroup group,
            @RequestParam(required = false) UserLevel level,
            @RequestParam(required = false) UserStatus status) {
        UserCondition condition = new UserCondition();
        condition.setUsername(username);
        condition.setRealName(realName);
        condition.setPhone(phone);
        condition.setGroup(group);
        condition.setLevel(level);
        condition.setStatus(status);

        return userBiz.list(condition);
    }

    /**
     * 用户详情
     *
     * @param username 用户名
     */
    @GetMapping("/info")
    public UserView query(String username) {
        return userBiz.query(username);
    }

    /**
     * 更新用户信息
     *
     * @param username 用户名
     */
    @PostMapping("/update")
    public UserView update(String username, @RequestBody UserUpdateReq req) {
        if (IUserContext.current().getLevel() != UserLevel.DIRECTOR
                && IUserContext.current().getLevel() != UserLevel.MANAGER) {
            throw new BizException("用户没有操作权限");
        }

        User user = new User();

        user.setUsername(username);
        user.setGroup(req.getGroup());
        user.setLevel(req.getLevel());
        user.setEmail(req.getEmail());
        user.setStatus(req.getStatus());

        return userBiz.update(user);
    }

    /**
     * 用户初始化，根据手机号绑定用户，初始化用户名
     *
     * @param phone 手机号
     */
    @PostMapping("/init")
    public UserView init(String phone, @RequestBody UserCompleteReq req) {
        if (IUserContext.current().getLevel() != UserLevel.DIRECTOR
                || IUserContext.current().getLevel() != UserLevel.MANAGER) {
            throw new BizException("用户没有操作权限");
        }
        User user = new User();

        user.setUsername(req.getUsername());
        user.setPhone(phone);
        user.setGroup(req.getGroup());
        user.setEmail(req.getEmail());

        return userBiz.init(user);
    }

    /**
     * 删除用户
     */
    @PostMapping("/delete")
    public void delete(String username) {
        if (IUserContext.current().getLevel() != UserLevel.DIRECTOR
                || IUserContext.current().getLevel() != UserLevel.MANAGER) {
            throw new BizException("用户没有操作权限");
        }
        userBiz.delete(username);
    }

    /**
     * 从 OA 工具同步用户
     */
    @GetMapping("/sync")
    public UserListView sync() {
        if (IUserContext.current().getLevel() != UserLevel.DIRECTOR
                || IUserContext.current().getLevel() != UserLevel.MANAGER) {
            throw new BizException("用户没有操作权限");
        }
        return userBiz.sync();
    }
}
