package com.fusm.servicebroker.servicebroker.service.ms_workflow;

import com.fusm.servicebroker.servicebroker.model.ms_workflow.EvaluateProposalModel;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.WorkflowRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IWorkFlowService {

    String evaluateProposal(EvaluateProposalModel evaluation, Integer proposalId);
    String declineOrDisableProgram(EvaluateProposalModel evaluation, Integer proposalId);
    Object getWorkflows();
    void updateWorkflow(WorkflowRequest workflowRequest, Integer workflowId);

}
