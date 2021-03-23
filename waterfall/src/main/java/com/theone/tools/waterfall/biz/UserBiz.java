package com.theone.tools.waterfall.biz;

import com.theone.tools.sso.client.IUserView;
import com.theone.tools.waterfall.model.user.User;
import com.theone.tools.waterfall.service.GlobalUserService;
import com.theone.tools.waterfall.vo.UserInfoResp;
import com.theone.tools.waterfall.vo.UserListResp;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserBiz {

    @Resource
    private GlobalUserService userService;

    public IUserView queryByToken(String token) {
        return IUserView.from(userService.ssoUser(token));
    }

    public UserListResp list() {
        List<User> list = userService.list();
        return new UserListResp(list.size(), list.stream().map(this::adapt).collect(Collectors.toList()));
    }

    public UserInfoResp adapt(User user) {
        UserInfoResp resp = new UserInfoResp();
        resp.setUsername(user.getUsername());
        resp.setRole(user.getUserRole());
        return resp;
    }

    public UserInfoResp info(String username) {
        userService.info(username);
        return null;
    }
}
