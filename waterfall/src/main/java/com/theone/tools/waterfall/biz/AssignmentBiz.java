package com.theone.tools.waterfall.biz;

import com.google.common.collect.Lists;
import com.theone.common.base.utils.DateFormatter;
import com.theone.tools.waterfall.model.assignment.Assignment;
import com.theone.tools.waterfall.model.assignment.AssignmentStatus;
import com.theone.tools.waterfall.model.assignment.AssignmentStruct;
import com.theone.tools.waterfall.model.assignment.AssignmentWorker;
import com.theone.tools.waterfall.model.requirement.RequirementStage;
import com.theone.tools.waterfall.service.AssignmentService;
import com.theone.tools.waterfall.service.RequirementStageService;
import com.theone.tools.waterfall.service.StatusManager;
import com.theone.tools.waterfall.vo.AssignmentInfoResp;
import com.theone.tools.waterfall.vo.AssignmentListResp;
import com.theone.tools.waterfall.vo.AssignmentWorkerInfoResp;
import com.theone.tools.waterfall.vo.AssignmentWorkersResp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author chenxiaotong
 */
@Service
public class AssignmentBiz {

    @Resource
    private AssignmentService assignmentService;
    @Resource
    private RequirementStageService requirementStageService;

    public void distribute(Integer assignmentId, List<String> username) {
        assignmentService.addWorker(assignmentId, username);
    }

    public void obtain(Integer assignmentId, String username) {
        assignmentService.addWorker(assignmentId, Lists.newArrayList(username));
    }

    public void complete(Integer assignmentId, String username) {
        assignmentService.complete(assignmentId, username);
    }

    public AssignmentWorkersResp workers(Integer assignmentId) {
        List<AssignmentWorker> workers = assignmentService.workers(assignmentId);
        List<AssignmentWorkerInfoResp> list = workers.stream()
                .map(this::assembleResp).collect(Collectors.toList());
        AssignmentWorkersResp resp = new AssignmentWorkersResp();
        resp.setWorkerList(list);
        return resp;
    }

    public void add(Integer stageId, String name, String desc, String expectDate) {
        RequirementStage stage = requirementStageService.info(stageId);
        Assignment assignment = new Assignment();
        assignment.setProjectId(stage.getProjectId());
        assignment.setRequirementId(stage.getRequirementId());
        assignment.setStageId(stage.getId());
        assignment.setStageType(stage.getType());
        assignment.setName(name);
        assignment.setAssignmentDesc(desc);
        assignment.setAssignmentStatus(AssignmentStatus.WAIT_ALLOCATE);
        assignment.setExpectDate(DateFormatter.parseDate(expectDate));

        assignmentService.add(assignment);
    }

    public void delete(Integer id) {
        assignmentService.delete(id);
    }

    public void update(Integer id, String name, String desc, String expectDate) {
        Assignment assignment = new Assignment();
        assignment.setId(id);
        assignment.setName(name);
        assignment.setAssignmentDesc(desc);
        assignment.setExpectDate(DateFormatter.parseDate(expectDate));

        assignmentService.updateInfo(assignment);
    }

    public AssignmentInfoResp info(Integer id) {
        AssignmentStruct struct = assignmentService.struct(id);
        return assembleResp(struct);
    }

    public AssignmentInfoResp assembleResp(AssignmentStruct assignmentStruct) {
        Assignment assignment = assignmentStruct.getAssignment();
        List<AssignmentWorker> structWorkers = assignmentStruct.getWorkers();
        AssignmentInfoResp assignmentInfoResp = new AssignmentInfoResp();
        assignmentInfoResp.setId(assignment.getId());
        assignmentInfoResp.setProjectId(assignment.getProjectId());
        assignmentInfoResp.setRequirementId(assignment.getRequirementId());
        assignmentInfoResp.setStageId(assignment.getStageId());
        assignmentInfoResp.setName(assignment.getName());
        assignmentInfoResp.setAssignmentDesc(assignment.getAssignmentDesc());
        assignmentInfoResp.setAssignmentStatus(assignment.getAssignmentStatus());
        assignmentInfoResp.setAssignmentStatusView(assignment.getAssignmentStatus().getDesc());
        assignmentInfoResp.setExpectTime(DateFormatter.format(assignment.getExpectDate()));

        List<AssignmentWorkerInfoResp> workerList = new ArrayList<>();
        for (AssignmentWorker worker : structWorkers) {
            AssignmentWorkerInfoResp workerInfoResp = assembleResp(worker);

            workerList.add(workerInfoResp);
        }
        assignmentInfoResp.setWorkerList(workerList);
        return assignmentInfoResp;
    }

    public AssignmentWorkerInfoResp assembleResp(AssignmentWorker structWorker) {
        AssignmentWorkerInfoResp workerInfoResp = new AssignmentWorkerInfoResp();
        workerInfoResp.setWorker(structWorker.getWorker());
        workerInfoResp.setWorkStatus(structWorker.getWorkStatus());
        workerInfoResp.setWorkStatusView(structWorker.getWorkStatus().getDesc());
        workerInfoResp.setStartTime(DateFormatter.format(structWorker.getStartTime()));
        workerInfoResp.setCompleteTime(DateFormatter.format(structWorker.getCompleteTime()));
        return workerInfoResp;
    }

    public AssignmentListResp list(Integer stageId) {
        List<AssignmentStruct> assignmentStructs = assignmentService.listStructByStage(stageId);
        List<AssignmentInfoResp> list = assignmentStructs.stream().map(this::assembleResp)
                .collect(Collectors.toList());

        AssignmentListResp resp = new AssignmentListResp();
        resp.setCount(list.size());
        resp.setList(list);

        return resp;
    }

    public void start(Integer assignmentId, String username) {
        assignmentService.start(assignmentId, username);
    }
}
