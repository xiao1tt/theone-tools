package com.theone.tools.waterfall.web;

import com.theone.tools.sso.client.IUserContext;
import com.theone.tools.waterfall.biz.AssignmentBiz;
import com.theone.tools.waterfall.vo.AssignmentAddReq;
import com.theone.tools.waterfall.vo.AssignmentDistributeReq;
import com.theone.tools.waterfall.vo.AssignmentInfoResp;
import com.theone.tools.waterfall.vo.AssignmentListResp;
import com.theone.tools.waterfall.vo.AssignmentUpdateReq;
import com.theone.tools.waterfall.vo.AssignmentWorkersResp;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenxiaotong
 */
@RestController
@RequestMapping("/assignment")
public class AssignmentController {

    @Resource
    private AssignmentBiz assignmentBiz;

    @PostMapping("/add")
    public void add(Integer stageId, @RequestBody AssignmentAddReq req) {
        assignmentBiz.add(stageId, req.getName(), req.getDesc(), req.getExpectDate());
    }

    @PostMapping("/delete")
    public void delete(Integer id) {
        assignmentBiz.delete(id);
    }

    @PostMapping("/update")
    public void update(Integer id, @RequestBody AssignmentUpdateReq req) {
        assignmentBiz.update(id, req.getName(), req.getDesc(), req.getExpectTime());
    }

    @GetMapping("/info")
    public AssignmentInfoResp info(Integer id) {
        return  assignmentBiz.info(id);
    }

    @GetMapping("/list")
    public AssignmentListResp list(Integer stageId) {
        return assignmentBiz.list(stageId);
    }

    @PostMapping("/distribute")
    public void distribute(Integer id, @RequestBody AssignmentDistributeReq req) {
        assignmentBiz.distribute(id, req.getUsers());
    }

    @PostMapping("/obtain")
    public void obtain(Integer id) {
        assignmentBiz.obtain(id, IUserContext.current().getUsername());
    }

    @PostMapping("/start")
    public void start(Integer id) {
        assignmentBiz.start(id, IUserContext.current().getUsername());
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
