package com.theone.tools.waterfall.service;

import com.theone.tools.waterfall.dao.RequirementAttachDao;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author chenxiaotong
 */
@Service
public class RequirementAttachService {

    @Resource
    private RequirementAttachDao attachDao;

    public void deleteByRequirement(int requirementId) {
        attachDao.deleteByRequirement(requirementId);
    }
}
