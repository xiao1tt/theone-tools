package com.theone.tools.waterfall.web;

import com.theone.tools.sso.client.IUserView;
import com.theone.tools.sso.client.SsoHelper;
import com.theone.tools.waterfall.biz.UserBiz;
import com.theone.tools.waterfall.vo.UserInfoResp;
import com.theone.tools.waterfall.vo.UserListResp;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户
 *
 * @author chenxiaotong
 */
@RestController
@RequestMapping(("/user"))
public class UserController {

    @Resource
    private UserBiz userBiz;

    /**
     * 当前用户
     */
    @GetMapping("/current")
    private IUserView current(HttpServletRequest request) {
        String token = SsoHelper.findToken(request);
        if (StringUtils.isBlank(token)) {
            return null;
        }
        return userBiz.queryByToken(token);
    }

    /**
     * 用户列表
     */
    @GetMapping("/list")
    private UserListResp list() {
        return userBiz.list();
    }

    /**
     * 用户信息
     */
    @GetMapping("/info")
    private UserInfoResp info(String username) {
        return userBiz.info(username);
    }


}
