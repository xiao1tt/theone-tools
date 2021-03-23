package com.theone.tools.waterfall.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.theone.common.base.utils.DateFormatter;
import com.theone.tools.waterfall.dao.RequirementDao;
import com.theone.tools.waterfall.entity.RequirementEntity;
import com.theone.tools.waterfall.model.assignment.Assignment;
import com.theone.tools.waterfall.model.project.Project;
import com.theone.tools.waterfall.model.requirement.Requirement;
import com.theone.tools.waterfall.model.requirement.RequirementStage;
import com.theone.tools.waterfall.model.requirement.RequirementStatus;
import com.theone.tools.waterfall.model.requirement.RequirementStruct;
import com.theone.tools.waterfall.model.requirement.StageType;
import com.theone.tools.waterfall.vo.RequirementDashboardProject;
import com.theone.tools.waterfall.vo.RequirementDashboardRequirement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author chenxiaotong
 */
@Service
public class RequirementService {

    @Resource
    private AssignmentService assignmentService;
    @Resource
    private ProjectService projectService;
    @Resource
    private RequirementAttachService requirementAttachService;
    @Resource
    private RequirementStageService requirementStageService;
    @Resource
    private RequirementDao requirementDao;

    public Requirement add(Requirement requirement) {
        RequirementEntity insert = adapt(requirement);
        requirementDao.insert(insert);
        return adapt(requirementDao.queryById(insert.getId()));
    }

    private Requirement adapt(RequirementEntity entity) {
        if (entity == null) {
            return null;
        }
        Requirement requirement = new Requirement();
        requirement.setId(entity.getId());
        requirement.setProjectId(entity.getProjectId());
        requirement.setTemplateId(entity.getTemplateId());
        requirement.setName(entity.getName());
        requirement.setRequirementDesc(entity.getRequirementDesc());
        requirement.setRequirementOwner(entity.getRequirementOwner());
        requirement.setPriority(entity.getPriority());
        requirement.setExpectDate(entity.getExpectDate());
        requirement.setCurrentStage(JSON.parseObject(entity.getCurrentStage(), RequirementStage.class));
        requirement.setCurrentAssignment(JSON.parseObject(entity.getCurrentAssignment(), Assignment.class));
        requirement.setRequirementStatus(entity.getRequirementStatus());
        requirement.setUpdateTime(entity.getUpdateTime());
        requirement.setCreateTime(entity.getCreateTime());

        return requirement;
    }

    private RequirementEntity adapt(Requirement requirement) {
        if (requirement == null) {
            return null;
        }
        RequirementEntity entity = new RequirementEntity();
        entity.setId(requirement.getId());
        entity.setProjectId(requirement.getProjectId());
        entity.setTemplateId(requirement.getTemplateId());
        entity.setName(requirement.getName());
        entity.setPriority(requirement.getPriority());
        entity.setExpectDate(requirement.getExpectDate());
        entity.setRequirementDesc(requirement.getRequirementDesc());
        entity.setRequirementOwner(requirement.getRequirementOwner());
        entity.setCurrentStage(JSON.toJSONString(requirement.getCurrentStage()));
        entity.setCurrentAssignment(JSON.toJSONString(requirement.getCurrentAssignment()));
        entity.setRequirementStatus(requirement.getRequirementStatus());
        entity.setUpdateTime(requirement.getUpdateTime());
        entity.setCreateTime(requirement.getCreateTime());
        return entity;
    }

    public void delete(int id) {
        requirementDao.deleteById(id);

        requirementStageService.deleteByRequirement(id);
        requirementAttachService.deleteByRequirement(id);
    }

    public void update(Requirement requirement) {
        requirementDao.update(adapt(requirement));
    }

    public Requirement info(int id) {
        RequirementEntity entity = requirementDao.queryById(id);
        return adapt(entity);
    }

    public List<RequirementStruct> listStruct(Integer projectId, String username, RequirementStatus status) {
        List<Assignment> assignments = assignmentService.listByProject(projectId, username);

        Map<Integer, List<Assignment>> requirementAssignmentMap = assignments.stream()
                .collect(Collectors.groupingBy(Assignment::getRequirementId));

        List<Requirement> requirements = this.listByIds(requirementAssignmentMap.keySet(), status);

        Map<Integer, Requirement> requirementMap = requirements.stream()
                .collect(Collectors.toMap(Requirement::getId, Function.identity()));

        List<RequirementStruct> result = new ArrayList<>();

        for (Integer id : requirementMap.keySet()) {
            RequirementStruct item = new RequirementStruct();
            item.setRequirement(requirementMap.get(id));
            item.setAssignments(requirementAssignmentMap.get(id));
            result.add(item);
        }

        return result;
    }

    private List<Requirement> listByIds(Collection<Integer> requirementIds, RequirementStatus status) {
        return requirementDao.queryByIds(requirementIds, status).stream().map(this::adapt).collect(Collectors.toList());
    }

    public Map<StageType, List<RequirementDashboardProject>> dashboard(String username, RequirementStatus status) {
        List<RequirementStruct> requirementStructList = this.listStruct(null, username, status);

        Set<Integer> projectIds = Sets.newHashSet();

        List<RequirementStruct> noStageRequirements = new ArrayList<>();

        Multimap<StageType, RequirementStruct> stageMultiMap = HashMultimap.create();
        for (RequirementStruct requirementStruct : requirementStructList) {
            Requirement requirementItem = requirementStruct.getRequirement();
            projectIds.add(requirementItem.getProjectId());

            if (requirementItem.getCurrentStage() == null) {
                noStageRequirements.add(requirementStruct);
            } else {
                stageMultiMap.put(requirementItem.getCurrentStage().getType(), requirementStruct);
            }
        }

        Map<StageType, Collection<RequirementStruct>> stageMap = stageMultiMap.asMap();

        Map<Integer, Project> projectIdMap = projectService.listByIds(projectIds).stream()
                .collect(Collectors.toMap(Project::getId, Function.identity()));

        Map<StageType, List<RequirementDashboardProject>> result = new HashMap<>(StageType.values().length);

        stageMap.forEach((stageType, stageRequirementsList) -> {
            List<RequirementDashboardProject> dashBoardProjects = new ArrayList<>();

            Multimap<Integer, RequirementDashboardRequirement> dashboardRequirementMap = convertToDashboardRequirementMap(
                    stageRequirementsList);

            for (Integer projectId : dashboardRequirementMap.keySet()) {
                Project project = projectIdMap.get(projectId);
                Collection<RequirementDashboardRequirement> boardRequirements = dashboardRequirementMap.get(projectId);

                RequirementDashboardProject dashBoardProject = new RequirementDashboardProject();
                dashBoardProject.setProjectId(project.getId());
                dashBoardProject.setProjectName(project.getName());
                dashBoardProject.setRequirements(Lists.newArrayList(boardRequirements));

                dashBoardProjects.add(dashBoardProject);
            }

            result.put(stageType, dashBoardProjects);
        });

        List<RequirementDashboardProject> dashBoardProjects = new ArrayList<>();
        Multimap<Integer, RequirementDashboardRequirement> dashboardRequirementMap = convertToDashboardRequirementMap(
                noStageRequirements);

        for (Integer projectId : dashboardRequirementMap.keySet()) {
            Project project = projectIdMap.get(projectId);
            Collection<RequirementDashboardRequirement> boardRequirements = dashboardRequirementMap.get(projectId);

            RequirementDashboardProject dashBoardProject = new RequirementDashboardProject();
            dashBoardProject.setProjectId(project.getId());
            dashBoardProject.setProjectName(project.getName());
            dashBoardProject.setRequirements(Lists.newArrayList(boardRequirements));

            dashBoardProjects.add(dashBoardProject);
        }

        result.put(null, dashBoardProjects);

        return result;
    }

    private Multimap<Integer, RequirementDashboardRequirement> convertToDashboardRequirementMap(
            Collection<RequirementStruct> stageRequirementsList) {
        Multimap<Integer, RequirementDashboardRequirement> dashboardRequirementMap = HashMultimap.create();

        for (RequirementStruct stageRequirement : stageRequirementsList) {
            Requirement requirement = stageRequirement.getRequirement();
            RequirementDashboardRequirement item = new RequirementDashboardRequirement();
            item.setName(requirement.getName());
            item.setDesc(requirement.getRequirementDesc());
            item.setPriority(requirement.getPriority());
            item.setOwner(requirement.getRequirementOwner());
            item.setExpectDate(DateFormatter.format(requirement.getExpectDate()));
            item.setStatus(requirement.getRequirementStatus());
            item.setStatusView(requirement.getRequirementStatus().getDesc());

            RequirementStage currentStage = requirement.getCurrentStage();
            if (currentStage != null) {
                item.setCurrentStage(currentStage.getType());
                item.setCurrentStageView(currentStage.getName());
                item.setCurrentWorker(assignmentService.currentWorker(currentStage.getId()));
            }

            dashboardRequirementMap.put(requirement.getProjectId(), item);
        }
        return dashboardRequirementMap;
    }
}
