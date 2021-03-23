package com.theone.tools.waterfall.biz;

import com.google.common.collect.Lists;
import com.theone.tools.waterfall.model.assignment.AssignmentStatus;
import com.theone.tools.waterfall.service.AssignmentService;
import com.theone.tools.waterfall.vo.AssignmentWorkersResp;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author chenxiaotong
 */
@Service
public class AssignmentBiz {

    @Resource
    private AssignmentService assignmentService;

    public void distribute(Integer assignmentId, List<String> username) {
        assignmentService.addWorker(assignmentId, username);
    }

    public void obtain(Integer assignmentId, String username) {
        assignmentService.addWorker(assignmentId, Lists.newArrayList(username));
    }

    public void complete(Integer assignmentId, String username) {
        assignmentService.updateWorkerStatus(assignmentId, username, AssignmentStatus.DONE);
    }

    public AssignmentWorkersResp workers(Integer assignmentId) {
        List<String> workers = assignmentService.currentWorker(assignmentId);
        return new AssignmentWorkersResp(workers);
    }
}
