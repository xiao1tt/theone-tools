package com.theone.tools.waterfall.task;

import com.theone.tools.waterfall.service.UserService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserSyncTask {
    @Resource
    private UserService userService;

    /**
     * 固定速率执行。每 3 分钟秒执行一次。
     */
    @Scheduled(fixedRate = 3 * 60 * 1000)
    public void sync() {
        userService.syncSsoUser();
    }
}
