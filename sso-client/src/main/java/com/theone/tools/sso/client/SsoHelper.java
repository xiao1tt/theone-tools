package com.theone.tools.sso.client;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class SsoHelper {

    public static String findToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }

        for (Cookie cookie : cookies) {
            if (SsoConstant.HORDE_COOKIE_KEY.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

    public static Cookie genCookie(String serverName, String token) {
        Cookie cookie = new Cookie(SsoConstant.HORDE_COOKIE_KEY, token);
        cookie.setPath("/");
        cookie.setDomain(serverName);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60 * 24 * 30 * 12);
        return cookie;
    }
}
