package com.theone.tools.sso.client;


import com.theone.common.base.json.JSON;
import com.theone.common.base.lang.APIResponse;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author chenxiaotong
 */
public abstract class BaseSsoFilter implements Filter {
    private final IUserClient userClient = new IUserClient();

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String requestURI = httpServletRequest.getRequestURI();

        if (ignore(requestURI)) {
            chain.doFilter(request, response);
            return;
        }

        String token = SsoHelper.findToken((HttpServletRequest) request);

        if (StringUtils.isBlank(token)) {
            unauthorized(httpServletResponse);
            return;
        }

        IUser user = userClient.query(token);
        if (user == null) {
            unauthorized(httpServletResponse);
            return;
        }

        if (user.getStatus() != UserStatus.AVAILABLE) {
            statusError(response, httpServletResponse);
            return;
        }

        setContext(user);
        try {
            before(user, httpServletRequest, httpServletResponse);
            chain.doFilter(request, response);
            after(user, httpServletRequest, httpServletResponse);
        } finally {
            clearContext();
        }
    }

    private void clearContext() {
        IUserContext.clear();
    }

    protected abstract void after(IUser user, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);

    protected abstract void before(IUser user, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);

    private void setContext(IUser user) {
        IUserContext.set(user);
    }

    private void statusError(ServletResponse response, HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();

        out.print(JSON.toJson(APIResponse.error(SsoConstant.USER_STATUS_EXCEPTION, "该用户未初始化或状态异常，请联系管理员")));
        out.flush();
        out.close();
    }


    private void unauthorized(HttpServletResponse response) {
        response.setStatus(401);
    }

    protected abstract boolean ignore(String requestURI);

    public void destroy() {

    }
}
