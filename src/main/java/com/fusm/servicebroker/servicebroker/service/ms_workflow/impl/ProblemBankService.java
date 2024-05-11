package com.fusm.servicebroker.servicebroker.service.ms_workflow.impl;

import com.fusm.servicebroker.servicebroker.model.ms_workflow.EvaluateProposalModel;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.ProblemBankRequest;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.SearchModelProblem;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.UserData;
import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.IProblemBankService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProblemBankService implements IProblemBankService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-workflow.complete-path}")
    private String WORKFLOW_ROUTE;

    @Value("${ms-workflow.workflow.path}")
    private String WORKFLOW_SERVICE;

    @Autowired
    private GlobalService globalService;


    @Override
    public void createProblemBank(ProblemBankRequest problemBankRequest) {
        problemBankRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        problemBankRequest.setRoleId(globalService.accessGlobalUserData().getRole());
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/problem")
                .bodyValue(problemBankRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Object getNifProblemBank(SearchModelProblem searchModel) {
        searchModel.setRoleId(globalService.accessGlobalUserData().getRole());
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/problem/nif")
                .bodyValue(searchModel)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public Object getProblemBank(SearchModelProblem searchModel) {
        searchModel.setRoleId(globalService.accessGlobalUserData().getRole());
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/problem/get")
                .bodyValue(searchModel)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public void updateProblemBank(ProblemBankRequest problemBankRequest, Integer problemBankId) {
        problemBankRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        problemBankRequest.setRoleId(globalService.accessGlobalUserData().getRole());
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .put()
                .uri(WORKFLOW_SERVICE + "/problem/" + problemBankId)
                .bodyValue(problemBankRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void evaluateProblemBank(EvaluateProposalModel evaluateProposalModel, Integer problemBankId) {
        evaluateProposalModel.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        evaluateProposalModel.setRoleId(globalService.accessGlobalUserData().getRole());
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/problem/" + problemBankId + "/evaluate")
                .bodyValue(evaluateProposalModel)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void enableDisableProblemBank(Integer problemBankId, Boolean enabled) {
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/problem/" + problemBankId + "/dis-enable/" + enabled)
                .bodyValue(
                        UserData.builder()
                                .userId(globalService.accessGlobalUserData().getEmail())
                                .userEmail(globalService.accessGlobalUserData().getEmail())
                                .roleId(globalService.accessGlobalUserData().getRole())
                                .build()
                )
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }


}
