package com.theone.tools.waterfall.web;

import com.theone.tools.waterfall.biz.RequirementBiz;
import com.theone.tools.waterfall.model.requirement.RequirementStatus;
import com.theone.tools.waterfall.vo.RequirementAddReq;
import com.theone.tools.waterfall.vo.RequirementDashboardResp;
import com.theone.tools.waterfall.vo.RequirementInfoResp;
import com.theone.tools.waterfall.vo.RequirementListResp;
import com.theone.tools.waterfall.vo.RequirementStagesResp;
import com.theone.tools.waterfall.vo.RequirementTemplateAddReq;
import com.theone.tools.waterfall.vo.RequirementTemplateInfoResp;
import com.theone.tools.waterfall.vo.RequirementTemplateListResp;
import com.theone.tools.waterfall.vo.RequirementTemplateUpdateReq;
import com.theone.tools.waterfall.vo.RequirementUpdateReq;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 需求管理
 *
 * @author chenxiaotong
 */
@RestController
@RequestMapping("/requirement")
public class RequirementController {

    @Resource
    private RequirementBiz requirementBiz;

    /**
     * 新增需求模版
     */
    @PostMapping("/template/add")
    public void templateAdd(@RequestBody RequirementTemplateAddReq req) {
        requirementBiz.templateAdd(req.getName(), req.getDesc(), req.getStages());
    }

    /**
     * 删除需求模版
     */
    @GetMapping("/template/delete")
    public void templateDelete(Integer id) {
        requirementBiz.templateDelete(id);
    }

    /**
     * 更新需求模版
     */
    @PostMapping("/template/update")
    public void templateUpdate(Integer id, @RequestBody RequirementTemplateUpdateReq req) {
        requirementBiz.templateUpdate(id, req.getName(),req.getDesc(),req.getStages());
    }

    /**
     * 查询需求模版信息
     */
    @GetMapping("/template/info")
    public RequirementTemplateInfoResp templateInfo(Integer id) {
        return requirementBiz.templateInfo(id);
    }

    /**
     * 查询需求模版列表
     */
    @GetMapping("/template/list")
    public RequirementTemplateListResp templateList() {
        return requirementBiz.templateList();
    }

    /**
     * 新建需求
     */
    @PostMapping("/add")
    public void add(@RequestBody RequirementAddReq req) {
        requirementBiz.add(req);
    }

    /**
     * 删除需求
     */
    @PostMapping("/delete")
    public void delete(Integer id) {
        requirementBiz.delete(id);
    }

    /**
     * 更新需求信息
     */
    @PostMapping("/update")
    public void update(Integer id, @RequestBody RequirementUpdateReq req) {
        requirementBiz.update(id, req.getName(), req.getDesc());
    }

    /**
     * 查询需求信息
     */
    @GetMapping("/info")
    public RequirementInfoResp info(Integer id) {
        return requirementBiz.info(id);
    }

    /**
     * 查询需求列表
     */
    @GetMapping("/list")
    public RequirementListResp list(@RequestParam(required = false) Integer projectId,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) RequirementStatus status) {
        return requirementBiz.list(projectId, username, status);
    }

    /**
     * 查询需求看板
     */
    @GetMapping("/dashboard")
    public RequirementDashboardResp dashboard(@RequestParam(required = false) String username,
            @RequestParam(required = false) RequirementStatus status) {
        return requirementBiz.dashboard(username, status);
    }

    @GetMapping("/stages")
    public RequirementStagesResp stages(Integer requirementId) {
        return requirementBiz.stages(requirementId);
    }
}
