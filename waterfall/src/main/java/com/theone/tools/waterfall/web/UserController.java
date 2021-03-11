package com.theone.tools.waterfall.web;

import com.theone.tools.sso.client.IUserContext;
import com.theone.tools.sso.client.SsoHelper;
import com.theone.tools.waterfall.model.user.User;
import com.theone.tools.waterfall.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author chenxiaotong
 */
@RestController("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/current")
    private User current(HttpServletRequest request) {
        String token = SsoHelper.findToken(request);
        if (StringUtils.isBlank(token)) {
            return null;
        }
        return userService.queryByToken(token);
    }

    @GetMapping("/list")
    private User list() {
        return userService.list(IUserContext.current().getUsername());
    }
}
