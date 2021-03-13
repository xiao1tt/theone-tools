package com.theone.tools.horde.task;

import com.theone.tools.horde.service.ImSyncService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author chenxiaotong
 */
@Component
public class ImSyncTask {

    @Resource
    private ImSyncService imSyncService;

    /**
     * 固定速率执行。每 3 分钟秒执行一次。
     */
    @Scheduled(fixedRate = 3 * 60 * 1000)
    public void sync() {
        imSyncService.sync();
    }
}
