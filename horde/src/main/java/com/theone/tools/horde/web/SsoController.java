package com.theone.tools.horde.web;

import com.google.common.base.Preconditions;
import com.theone.tools.horde.bean.LoginReq;
import com.theone.tools.horde.bean.User;
import com.theone.tools.horde.biz.SsoBiz;
import com.theone.tools.sso.client.IUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * sso
 *
 * @author chenxiaotong
 */
@RestController
@RequestMapping("/sso")
public class SsoController {

    @Resource
    private SsoBiz ssoBiz;

    /**
     * sso 登录，密码 MD5 加密（小写、无横杠）
     */
    @PostMapping("/login")
    public User login(@RequestBody(required = false) LoginReq req, HttpServletRequest request, HttpServletResponse response) {
        Preconditions.checkArgument(req != null, "请求不能为空");
        Preconditions.checkArgument(StringUtils.isNotBlank(req.getUsername()), "账号不能为空");
        Preconditions.checkArgument(StringUtils.isNotBlank(req.getPassword()), "密码不能为空");

        return ssoBiz.login(req.getUsername(), req.getPassword(), request, response);
    }

    /**
     * 登出
     */
    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        ssoBiz.logout(request);
    }

    /**
     * 当前登录用户
     */
    @GetMapping("/current")
    public User current(HttpServletRequest request) {
        return ssoBiz.current(request);
    }

    /**
     * 用户列表
     */
    @GetMapping("/list")
    public List<IUser> list() {
        return ssoBiz.list();
    }

    /**
     * 令牌授权
     */
    @GetMapping("/auth")
    public IUser auth(String token) {
        Preconditions.checkArgument(StringUtils.isNotBlank(token), "token不能为空");

        return ssoBiz.auth(token);
    }
}
