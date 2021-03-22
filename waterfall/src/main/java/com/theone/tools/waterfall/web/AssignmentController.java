package com.theone.tools.waterfall.web;

import com.theone.tools.sso.client.IUserContext;
import com.theone.tools.waterfall.biz.AssignmentBiz;
import com.theone.tools.waterfall.vo.AssignmentDistributeReq;
import com.theone.tools.waterfall.vo.AssignmentWorkersResp;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assignment")
public class AssignmentController {

    @Resource
    private AssignmentBiz assignmentBiz;

    @PostMapping("/distribute")
    public void distribute(Integer assignmentId, @RequestBody AssignmentDistributeReq req) {
        assignmentBiz.distribute(assignmentId, req.getUsers());
    }

    @PostMapping("/obtain")
    public void obtain(Integer assignmentId) {
        assignmentBiz.obtain(assignmentId, IUserContext.current().getUsername());
    }

    @PostMapping("/complete")
    public void complete(Integer assignmentId) {
        assignmentBiz.complete(assignmentId, IUserContext.current().getUsername());
    }

    @GetMapping("/workers")
    public AssignmentWorkersResp workers(Integer assignmentId) {
        return assignmentBiz.workers(assignmentId);
    }
}
