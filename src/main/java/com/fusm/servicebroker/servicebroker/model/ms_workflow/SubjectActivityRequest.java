package com.fusm.servicebroker.servicebroker.model.ms_workflow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectActivityRequest {

    private Integer subjectActivityId;
    private Integer session;
    private Date activityDate;
    private String result;
    private String topic;
    private String syncActivities;
    private String previusActivities;
    private String strategies;
    private String url;
    private Boolean enabled;
    private String createdBy;
    private Integer roleId;
    private Boolean canUpdate;;

}
