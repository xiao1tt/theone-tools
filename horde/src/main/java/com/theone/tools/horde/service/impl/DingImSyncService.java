package com.theone.tools.horde.service.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.theone.tools.horde.bean.Im.DingUserInfoResp;
import com.theone.tools.horde.bean.Im.DingUserSimple;
import com.theone.tools.horde.bean.User;
import com.theone.tools.horde.service.ImRemoteService;
import com.theone.tools.horde.service.ImSyncService;
import com.theone.tools.horde.service.UserService;
import com.theone.tools.sso.client.UserGroup;
import com.theone.tools.sso.client.UserLevel;
import com.theone.tools.sso.client.UserStatus;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author chenxiaotong
 */
@Service
public class DingImSyncService implements ImSyncService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ImRemoteService imRemoteService;
    @Resource
    private UserService userService;

    @Override
    public void sync() {
        List<DingUserSimple> userIdList = imRemoteService.userList(token());
        for (DingUserSimple userId : userIdList) {
            DingUserInfoResp userInfo = imRemoteService.userInfo(token(), userId.getUserid());
            User exist = userService.queryByPhone(userInfo.getMobile());
            if (exist != null) {
                boolean update = false;
                if (!StringUtils.equals(exist.getRealName(), userInfo.getName())) {
                    exist.setRealName(userInfo.getName());
                    update = true;
                }
                if (!StringUtils.equals(exist.getAvatar(), userInfo.getAvatar())) {
                    exist.setAvatar(userInfo.getAvatar());
                    update = true;
                }
                if (update) {
                    userService.updateByPhone(exist);
                }
            } else {
                User insert = new User();
                insert.setPhone(userInfo.getMobile());
                insert.setGroup(UserGroup.UNKNOWN);
                insert.setLevel(UserLevel.UNKNOWN);
                insert.setRealName(userInfo.getName());
                insert.setAvatar(userInfo.getAvatar());
                insert.setStatus(UserStatus.WAIT_COMPLETED);

                userService.insert(insert);
            }
        }
    }

    private final LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder()
            .expireAfterWrite(3, TimeUnit.MINUTES)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String s) {
                    return imRemoteService.token();
                }
            });

    public String token() {
        try {
            return loadingCache.get("token");
        } catch (ExecutionException e) {
            logger.error("缓存获取 token 失败", e);
            return imRemoteService.token();
        }
    }
}
