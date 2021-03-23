package com.theone.tools.waterfall.service;

import com.theone.common.base.lang.BizException;
import com.theone.tools.sso.client.IUser;
import com.theone.tools.sso.client.IUserClient;
import com.theone.tools.sso.client.UserStatus;
import com.theone.tools.waterfall.dao.UserDao;
import com.theone.tools.waterfall.entity.UserEntity;
import com.theone.tools.waterfall.model.user.Role;
import com.theone.tools.waterfall.model.user.User;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author chenxiaotong
 */
@Service
public class GlobalUserService {

    @Resource
    private UserDao userDao;

    private final IUserClient userClient = new IUserClient();

    public void syncSsoUser() {
        List<IUser> all = userClient.all();
        for (IUser iUser : all) {
            if (this.exist(iUser.getUsername())) {
                continue;
            }

            UserEntity entity = new UserEntity();
            entity.setUsername(iUser.getUsername());
            entity.setUserRole(Role.WORKER);
            userDao.insert(entity);
        }
    }

    private boolean exist(String username) {
        return userDao.query(username) != null;
    }

    public IUser ssoUser(String token) {
        IUser iUser = userClient.query(token);
        if (iUser != null && iUser.getStatus() == UserStatus.AVAILABLE) {
            return iUser;
        }
        throw new BizException("无法获取用户信息，用户不存在或状态异常");
    }

    private User adapt(UserEntity entity) {
        if (entity == null) {
            return null;
        }

        User user = new User();
        user.setUsername(entity.getUsername());
        user.setUserRole(entity.getUserRole());
        return user;
    }

    public List<User> list() {
        List<UserEntity> entities = userDao.all();
        return entities.stream().map(this::adapt).collect(Collectors.toList());
    }

    public User info(String username) {
        UserEntity entity = userDao.query(username);
        return adapt(entity);
    }

    public List<User> listByUsername(List<String> users) {
        List<UserEntity> userEntities = userDao.listByUsername(users);
        return userEntities.stream().map(this::adapt).collect(Collectors.toList());
    }
}
