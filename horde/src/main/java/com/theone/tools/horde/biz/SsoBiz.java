package com.theone.tools.horde.biz;

import com.theone.common.base.lang.BizException;
import com.theone.tools.horde.bean.Session;
import com.theone.tools.horde.bean.User;
import com.theone.tools.horde.service.PasswordService;
import com.theone.tools.horde.service.SessionService;
import com.theone.tools.horde.service.UserService;
import com.theone.tools.horde.utils.CryptoUtil;
import com.theone.tools.sso.client.IUser;
import com.theone.tools.sso.client.SsoHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chenxiaotong
 */
@Service
public class SsoBiz {


    @Resource
    private UserService userService;
    @Resource
    private PasswordService passwordService;
    @Resource
    private SessionService sessionService;

    public User login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        boolean available = userService.available(username);
        if (!available) {
            throw new BizException("该账号不存在或不可用");
        }

        boolean pass = passwordService.check(username, password);
        if (!pass) {
            throw new BizException("密码错误");
        }

        User user = userService.query(username);

        String token = CryptoUtil.encryptHex(username + System.currentTimeMillis());

        Cookie cookie = SsoHelper.genCookie(request.getServerName(), token);
        response.addCookie(cookie);

        sessionService.save(username, token);
        return user;
    }

    public void logout(HttpServletRequest request) {
        String token = SsoHelper.findToken(request);
        if (token != null) {
            sessionService.clearByToken(token);
        }
    }

    public IUser auth(String token) {
        User user = findUser(token);
        if (user == null) {
            return null;
        }

        IUser iUser = new IUser();
        iUser.setUsername(user.getUsername());
        iUser.setPhone(user.getPhone());
        iUser.setGroup(user.getGroup());
        iUser.setLevel(user.getLevel());
        iUser.setRealName(user.getRealName());
        iUser.setEmail(user.getEmail());
        iUser.setAvatar(user.getAvatar());
        iUser.setStatus(user.getStatus());
        iUser.setCreateTime(user.getCreateTime());

        return iUser;
    }

    public User current(HttpServletRequest request) {
        String token = SsoHelper.findToken(request);
        return findUser(token);
    }

    private User findUser(String token) {
        Session session = sessionService.queryByToken(token);
        if (session == null) {
            return null;
        }

        String username = session.getUsername();
        return userService.query(username);
    }
}
