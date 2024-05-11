package com.fusm.servicebroker.servicebroker.service.ms_workflow;

import com.fusm.servicebroker.servicebroker.model.ms_workflow.StepRoleActionRequest;
import org.springframework.stereotype.Service;


@Service
public interface IStepRoleActionService {

    Object getRolesRelatedWithStep(Integer stepId);
    void deleteRoleFromStep(Integer roleId, Integer stepId);
    void addRoleActionToStep(Integer stepId, StepRoleActionRequest roleActions);
    void updateActionToRole(Integer stepId, StepRoleActionRequest roleActions);

}