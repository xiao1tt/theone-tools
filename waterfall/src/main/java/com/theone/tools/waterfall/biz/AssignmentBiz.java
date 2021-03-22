package com.theone.tools.waterfall.biz;

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
        assignmentService.updateStatus(assignmentId, AssignmentStatus.DOING);
    }

    public void obtain(Integer assignmentId, String username) {

    }

    public void complete(Integer assignmentId, String username) {

    }

    public AssignmentWorkersResp workers(Integer assignmentId) {
        List<String> workers = assignmentService.currentWorker(assignmentId);
        return new AssignmentWorkersResp(workers);
    }
}
