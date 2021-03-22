package com.theone.tools.horde.service.impl;

import com.theone.tools.horde.bean.User;
import com.theone.tools.horde.bean.UserCondition;
import com.theone.tools.horde.dao.UserDao;
import com.theone.tools.horde.entity.UserEntity;
import com.theone.tools.horde.service.UserService;
import com.theone.tools.sso.client.UserStatus;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2021-02-25 13:54:18
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User query(String username) {
        UserEntity entity = userDao.query(username);

        return adapt(entity);
    }

    private User adapt(UserEntity entity) {
        if (entity == null) {
            return null;
        }

        User user = new User();
        user.setUsername(entity.getUsername());
        user.setPhone(entity.getPhone());
        user.setGroup(entity.getGroup());
        user.setLevel(entity.getLevel());
        user.setRealName(entity.getRealName());
        user.setEmail(entity.getEmail());
        user.setAvatar(entity.getAvatar());
        user.setStatus(entity.getStatus());
        user.setCreateTime(entity.getCreateTime());
        return user;
    }

    @Override
    public List<User> list(UserCondition condition) {
        return this.userDao.list(condition).stream().map(this::adapt).collect(Collectors.toList());
    }

    @Override
    public void insert(User user) {
        this.userDao.insert(adapt(user));
    }

    @Override
    public User update(User user) {
        if (StringUtils.isBlank(user.getUsername())) {
            return null;
        }

        int update = this.userDao.update(adapt(user));
        if (update == 0) {
            return null;
        }

        return this.query(user.getUsername());
    }

    @Override
    public User updateByPhone(User user) {
        if (StringUtils.isBlank(user.getPhone())) {
            return null;
        }

        int update = userDao.updateByPhone(adapt(user));

        if (update == 0) {
            return null;
        }

        return this.queryByPhone(user.getPhone());
    }

    private UserEntity adapt(User user) {
        if (user == null) {
            return null;
        }

        UserEntity entity = new UserEntity();
        entity.setUsername(user.getUsername());
        entity.setPhone(user.getPhone());
        entity.setGroup(user.getGroup());
        entity.setLevel(user.getLevel());
        entity.setRealName(user.getRealName());
        entity.setEmail(user.getEmail());
        entity.setAvatar(user.getAvatar());
        entity.setStatus(user.getStatus());
        return entity;
    }

    @Override
    public void delete(String username) {
        userDao.delete(username);
    }

    @Override
    public User queryByPhone(String mobile) {
        return adapt(userDao.queryByPhone(mobile));
    }

}