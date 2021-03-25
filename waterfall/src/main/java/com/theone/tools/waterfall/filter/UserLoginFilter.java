package com.theone.tools.waterfall.filter;

import com.theone.tools.sso.client.BaseSsoFilter;
import com.theone.tools.sso.client.IUser;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

/**
 * @author chenxiaotong
 */
@WebFilter(filterName = "userLoginFilter", urlPatterns = {"/waterfall/*"})
public class UserLoginFilter extends BaseSsoFilter {

    @Override
    protected void after(IUser user, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

    }

    @Override
    protected void before(IUser user, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

    }

    @Override
    protected boolean ignore(String requestURI) {
        return false;
    }
}
