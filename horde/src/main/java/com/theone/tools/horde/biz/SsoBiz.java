package com.theone.tools.horde.biz;

import com.theone.common.base.lang.BizException;
import com.theone.tools.horde.bean.Session;
import com.theone.tools.horde.bean.User;
import com.theone.tools.horde.bean.UserCondition;
import com.theone.tools.horde.entity.PasswordEntity;
import com.theone.tools.horde.service.PasswordService;
import com.theone.tools.horde.service.SessionService;
import com.theone.tools.horde.service.UserService;
import com.theone.tools.horde.utils.CryptoUtil;
import com.theone.tools.sso.client.IUser;
import com.theone.tools.sso.client.SsoHelper;
import com.theone.tools.sso.client.UserStatus;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        User userExist = userService.query(username);

        if (userExist == null) {
            User phoneExist = userService.queryByPhone(username);

            if (phoneExist == null) {
                throw new BizException("该用户不存在");
            }

            return phoneExist;
        }

        if (UserStatus.AVAILABLE != userExist.getStatus()) {
            throw new BizException("该账号状态不可用");
        }

        boolean pass = passwordService.check(username, password);
        if (!pass) {
            throw new BizException("密码错误");
        }

        String token = CryptoUtil.encryptHex(username + System.currentTimeMillis());

        Cookie cookie = SsoHelper.genCookie(request.getServerName(), token);
        response.addCookie(cookie);

        sessionService.save(username, token);

        return userExist;
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

        return adapt(user);
    }

    private IUser adapt(User user) {
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

        if (session.getCreateTime().isBefore(LocalDateTime.now().minusDays(30))) {
            sessionService.clearByToken(token);
        }

        String username = session.getUsername();
        return userService.query(username);
    }

    public List<IUser> list() {
        List<User> list = userService.list(new UserCondition());
        return list.stream()
                .filter(user -> StringUtils.isNotBlank(user.getUsername()))
                .map(this::adapt).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Throwable.class)
    public void passwordUpdate(String username, String currentPassword, String newPassword, String confirmPassword) {
        if (StringUtils.isBlank(newPassword) || !StringUtils.equals(newPassword, confirmPassword)) {
            throw new BizException("新密码为空，或两次密码不相同");
        }

        boolean check = passwordService.check(username, currentPassword);
        if (!check) {
            throw new BizException("当前密码错误");
        }

        PasswordEntity update = new PasswordEntity();
        update.setUsername(username);
        update.setPassword(newPassword);

        passwordService.update(update);
        sessionService.clearByUser(username);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void init(String phone, String username, String password) {
        User exist = userService.queryByPhone(phone);
        exist.setUsername(username);
        exist.setStatus(UserStatus.AVAILABLE);
        userService.updateByPhone(exist);

        PasswordEntity passwordEntity = new PasswordEntity();
        passwordEntity.setUsername(username);
        passwordEntity.setPassword(password);
        passwordService.insert(passwordEntity);
    }
}
