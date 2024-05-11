package com.fusm.servicebroker.servicebroker.service.ms_workflow;

import com.fusm.servicebroker.servicebroker.model.ms_workflow.IntegrativeActivityRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IIntegrativeActivityService {

    void createActivity(List<IntegrativeActivityRequest> activityRequestList, Integer curriculumId);
    void updateActivity(IntegrativeActivityRequest activityRequest, Integer activityId);
    void disableActivity(Integer activityId);

}
