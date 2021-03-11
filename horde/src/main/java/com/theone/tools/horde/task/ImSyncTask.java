package com.theone.tools.horde.task;

import com.theone.tools.horde.service.ImSyncService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author chenxiaotong
 */
@Component
public class ImSyncTask {

    @Resource
    private ImSyncService imSyncService;

    public void sync() {
        imSyncService.sync();
    }
}
