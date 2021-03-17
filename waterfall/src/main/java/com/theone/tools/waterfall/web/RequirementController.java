package com.theone.tools.waterfall.web;

import com.theone.tools.waterfall.biz.RequirementBiz;
import com.theone.tools.waterfall.model.requirement.RequirementStatus;
import com.theone.tools.waterfall.vo.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author chenxiaotong
 */
@RestController
@RequestMapping("/requirement")
public class RequirementController {

    @Resource
    private RequirementBiz requirementBiz;

    @PostMapping("/template/add")
    public void templateAdd(@RequestBody RequirementTemplateAddReq req) {
        requirementBiz.templateAdd(req.getName(), req.getDesc(), req.getStages());
    }

    @GetMapping("/template/delete")
    public void templateDelete(Integer templateId) {
        requirementBiz.templateDelete(templateId);
    }

    @PostMapping("/template/update")
    public void templateUpdate(Integer templateId, @RequestBody RequirementTemplateUpdateReq req) {
        requirementBiz.templateUpdate(templateId, req);
    }

    @GetMapping("/template/info")
    public RequirementTemplateInfoResp templateInfo(Integer id) {
        return requirementBiz.templateInfo(id);
    }

    @GetMapping("/template/list")
    public RequirementTemplateListResp templateList() {
        return requirementBiz.templateList();
    }

    @PostMapping("/add")
    public void add(@RequestBody RequirementAddReq req) {
        requirementBiz.add(req);
    }

    @GetMapping("/delete")
    public void delete(Integer id) {
        requirementBiz.delete(id);
    }

    @PostMapping("/update")
    public void update(Integer id, @RequestBody RequirementUpdateReq req) {
        requirementBiz.update(id, req.getName(), req.getDesc());
    }

    @GetMapping("/info")
    public RequirementInfoResp info(Integer id) {
        return requirementBiz.info(id);
    }

    @GetMapping("/list")
    public RequirementListResp list(@RequestParam(required = false) Integer projectId,
                                    @RequestParam(required = false) String username,
                                    @RequestParam(required = false) RequirementStatus status) {
        return requirementBiz.list(projectId, username, status);
    }

    @GetMapping("/dashboard")
    public RequirementDashboardResp dashboard(@RequestParam(required = false) String username,
                                              @RequestParam(required = false) RequirementStatus status) {
        return requirementBiz.dashboard(username, status);
    }
}
