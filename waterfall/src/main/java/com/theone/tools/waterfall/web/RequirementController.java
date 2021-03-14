package com.theone.tools.waterfall.web;

import com.theone.tools.waterfall.biz.RequirementBiz;
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
    public void templateDelete(int templateId) {
        requirementBiz.templateDelete(templateId);
    }

    @PostMapping("/template/update")
    public void templateUpdate(int templateId, @RequestBody RequirementTemplateUpdateReq req) {
        requirementBiz.templateUpdate(templateId, req);
    }

    @GetMapping("/template/info")
    public RequirementTemplateInfoResp templateInfo(int id) {
        return requirementBiz.templateInfo(id);
    }

    @GetMapping("/template/list")
    public RequirementTemplateListResp templateList() {
        return requirementBiz.templateList();
    }

    @PostMapping("/add")
    public void add(@RequestBody RequirementAddReq req) {
        requirementBiz.add(req.getProjectId(), req.getTemplateId(), req.getName(), req.getDesc());
    }

    @GetMapping("/delete")
    public void delete(int id) {
        requirementBiz.delete(id);
    }

    @PostMapping("/update")
    public void update(int id, @RequestBody RequirementUpdateReq req) {
        requirementBiz.update(id, req.getName(), req.getDesc());
    }

    @GetMapping("/info")
    public RequirementInfoResp info(int id) {
        return requirementBiz.info(id);
    }
}
