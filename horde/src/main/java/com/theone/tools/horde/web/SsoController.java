package com.theone.tools.horde.web;

import com.google.common.base.Preconditions;
import com.theone.tools.horde.bean.LoginReq;
import com.theone.tools.horde.bean.PasswordUpdateReq;
import com.theone.tools.horde.bean.User;
import com.theone.tools.horde.bean.UserInitReq;
import com.theone.tools.horde.biz.SsoBiz;
import com.theone.tools.sso.client.IUser;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public User login(@RequestBody(required = false) LoginReq req, HttpServletRequest request,
            HttpServletResponse response) {
        Preconditions.checkArgument(req != null, "请求不能为空");
        Preconditions.checkArgument(StringUtils.isNotBlank(req.getUsername()), "账号不能为空");
        Preconditions.checkArgument(StringUtils.isNotBlank(req.getPassword()), "密码不能为空");

        return ssoBiz.login(req.getUsername(), req.getPassword(), request, response);
    }

    /**
     * 更新密码
     */
    @PostMapping("/password/update")
    public void passwordUpdate(String username, @RequestBody PasswordUpdateReq req, HttpServletRequest request,
            HttpServletResponse response) {
        Preconditions.checkArgument(req != null, "请求不能为空");
        Preconditions.checkArgument(StringUtils.isNotBlank(req.getCurrentPassword()), "当前密码不能为空");
        Preconditions.checkArgument(StringUtils.isNotBlank(req.getNewPassword()), "新密码不能为空");
        Preconditions.checkArgument(StringUtils.isNotBlank(req.getConfirmPassword()), "确认密码不能为空");

        ssoBiz.passwordUpdate(username, req.getCurrentPassword(), req.getNewPassword(), req.getConfirmPassword());
    }

    @PostMapping("/init")
    public void init(String phone, @RequestBody UserInitReq req) {
        Preconditions.checkArgument(req != null, "请求不能为空");
        Preconditions.checkArgument(StringUtils.isNotBlank(req.getUsername()), "用户名不能为空");
        Preconditions.checkArgument(StringUtils.isNotBlank(req.getPassword()), "密码不能为空");
        Preconditions.checkArgument(StringUtils.equals(req.getPassword(), req.getConfirmPassword()), "两次密码不一致");

        ssoBiz.init(phone, req.getUsername(), req.getPassword());
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
