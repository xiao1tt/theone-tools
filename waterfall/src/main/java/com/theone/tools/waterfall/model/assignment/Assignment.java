package com.theone.tools.waterfall.model.assignment;

import com.theone.tools.sso.client.UserGroup;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author chenxiaotong
 */
public class Assignment {
    private String name;

    private String desc;

    private UserGroup requiredGroup;

    private List<String> workers;

    private AssignmentStatus status;

    private LocalDateTime startTime;
    private LocalDateTime completeTime;


}
