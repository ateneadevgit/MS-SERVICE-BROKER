package com.fusm.servicebroker.servicebroker.service.ms_program.impl;

import com.fusm.servicebroker.servicebroker.model.ms_program.EvaluateProposalRenovationRequest;
import com.fusm.servicebroker.servicebroker.model.ms_program.ProposalUpgradeRequest;
import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.service.ms_program.IProposalRenovationService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProposalRenovationService implements IProposalRenovationService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-program.complete-path}")
    private String PROGRAM_ROUTE;

    @Value("${ms-program.programs.path}")
    private String PROGRAMS_SERVICE;

    @Autowired
    private GlobalService globalService;


    @Override
    public void createProposalRenovation(ProposalUpgradeRequest proposalUpgradeRequest, Integer programId) {
        proposalUpgradeRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        proposalUpgradeRequest.setRoleId(globalService.accessGlobalUserData().getRole());
        webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .post()
                .uri(PROGRAMS_SERVICE + "/upgrade/" + programId)
                .bodyValue(proposalUpgradeRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void evaluateProposalRenovation(EvaluateProposalRenovationRequest evaluateProposalRenovationRequest, Integer programId) {
        evaluateProposalRenovationRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        evaluateProposalRenovationRequest.setRoleId(globalService.accessGlobalUserData().getRole());
        webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .post()
                .uri(PROGRAMS_SERVICE + "/upgrade/evaluate/" + programId)
                .bodyValue(evaluateProposalRenovationRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Object getRequestProposal(Integer programId) {
        return webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .get()
                .uri(PROGRAMS_SERVICE + "/upgrade/program-id/" + programId)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public Object getSelectedModules(Integer programId) {
        return webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .get()
                .uri(PROGRAMS_SERVICE + "/upgrade/evaluate/" + programId)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public Object getOnEditionModules(Integer programId) {
        return webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .get()
                .uri(PROGRAMS_SERVICE + "/upgrade/edition/" + programId)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

}
