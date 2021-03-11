package com.theone.tools.sso.client;

/**
 * @author chenxiaotong
 */
public class IUserContext {
    private static InheritableThreadLocal<IUser> userThreadLocal = new InheritableThreadLocal<IUser>();

    public static IUser current() {
        return userThreadLocal.get();
    }

    public static void set(IUser user) {
        userThreadLocal.set(user);
    }

    public static void clear() {
        userThreadLocal.remove();
    }
}
