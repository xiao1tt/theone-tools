package com.theone.tools.waterfall.service;

import com.theone.common.base.lang.BizException;
import com.theone.tools.sso.client.IUser;
import com.theone.tools.sso.client.IUserClient;
import com.theone.tools.sso.client.UserStatus;
import com.theone.tools.waterfall.model.user.User;
import org.springframework.stereotype.Service;

/**
 * @author chenxiaotong
 */
@Service
public class UserService {
    private final IUserClient userClient = new IUserClient();

    public User queryByToken(String token) {
        IUser iUser = userClient.query(token);
        if (iUser != null && iUser.getStatus() == UserStatus.AVAILABLE) {
            return adapt(iUser);
        }
        throw new BizException("无法获取用户信息，用户不存在或状态异常");
    }

    private User adapt(IUser iUser) {
        User user = new User();
        user.setUsername(iUser.getUsername());
        user.setPhone(iUser.getPhone());
        user.setGroup(iUser.getGroup());
        user.setGroup(iUser.getGroup());
        user.setGroupView(iUser.getGroup().getDesc());
        user.setLevel(iUser.getLevel());
        user.setRealName(iUser.getRealName());
        user.setEmail(iUser.getEmail());
        user.setAvatar(iUser.getAvatar());
        user.setStatus(iUser.getStatus());
        user.setStatusView(iUser.getStatus().getDesc());

        return user;
    }

    public User list(String username) {

        return null;
    }
}
