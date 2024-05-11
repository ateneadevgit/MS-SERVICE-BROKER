package com.fusm.servicebroker.servicebroker.service.ms_workflow;

import com.fusm.servicebroker.servicebroker.model.ms_workflow.AssignCoordinator;
import org.springframework.stereotype.Service;

@Service
public interface ICurriculumCoordinatorService {

    void assignCoordinator(AssignCoordinator assignCoordinator, Integer subjectId);
    Object coordinatorAssigned(Integer subjectId);

}

