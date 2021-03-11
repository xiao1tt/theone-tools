package com.theone.tools.horde.biz;

import com.theone.common.base.lang.BizException;
import com.theone.common.base.lang.DateFormatter;
import com.theone.tools.horde.bean.User;
import com.theone.tools.horde.bean.UserCondition;
import com.theone.tools.horde.bean.UserListView;
import com.theone.tools.horde.bean.UserView;
import com.theone.tools.horde.service.ImSyncService;
import com.theone.tools.horde.service.UserService;
import com.theone.tools.sso.client.UserStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chenxiaotong
 */
@Service
public class UserBiz {
    @Resource
    private UserService userService;
    @Resource
    private ImSyncService imSyncService;

    public UserView query(String username) {
        User user = userService.query(username);
        return adapt(user);
    }

    public UserListView list(UserCondition condition) {
        List<User> users = userService.list(condition);
        List<UserView> list = users.stream().map(this::adapt).collect(Collectors.toList());
        return new UserListView(list.size(), list);
    }

    private UserView adapt(User user) {
        if (user == null) {
            return null;
        }

        UserView view = new UserView();
        view.setUsername(user.getUsername());
        view.setPhone(user.getPhone());
        view.setGroup(user.getGroup());
        view.setGroupView(user.getGroup().getDesc());
        view.setLevel(user.getLevel());
        view.setLevelView(user.getLevel().getDesc());
        view.setRealName(user.getRealName());
        view.setEmail(user.getEmail());
        view.setStatus(user.getStatus());
        view.setStatusView(user.getStatus().getDesc());
        view.setCreateTime(DateFormatter.format(user.getCreateTime()));

        return view;
    }

    public UserView update(User user) {
        User update = userService.update(user);
        return adapt(update);
    }

    public void delete(String username) {
        userService.delete(username);
    }

    public UserListView sync() {
        imSyncService.sync();
        return this.list(new UserCondition());
    }

    public UserView init(User user) {
        User exist = userService.queryByPhone(user.getPhone());
        if (exist != null && !UserStatus.WAIT_COMPLETED.equals(exist.getStatus())) {
            throw new BizException("该用户已初始化");
        }

        user.setStatus(UserStatus.AVAILABLE);
        User now = userService.updateByPhone(user);
        return adapt(now);
    }
}
