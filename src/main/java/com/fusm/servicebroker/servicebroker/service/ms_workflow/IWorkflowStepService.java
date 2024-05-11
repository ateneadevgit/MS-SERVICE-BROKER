package com.fusm.servicebroker.servicebroker.service.ms_workflow;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IWorkflowStepService {

    Object getStepsByWorkflow(Integer workflowId);

}
