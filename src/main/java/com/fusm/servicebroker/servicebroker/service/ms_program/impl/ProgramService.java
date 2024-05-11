package com.fusm.servicebroker.servicebroker.service.ms_program.impl;

import com.fusm.servicebroker.servicebroker.model.ms_program.*;
import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.service.ms_program.IProgramService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ProgramService implements IProgramService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-program.complete-path}")
    private String PROGRAM_ROUTE;

    @Value("${ms-program.programs.path}")
    private String PROGRAMS_SERVICE;

    @Autowired
    private GlobalService globalService;


    @Override
    public Object getProgram() {
        return webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .get()
                .uri(PROGRAMS_SERVICE + "/faculty-id/" + ((globalService.accessGlobalUserData().getFaculty() != null) ?
                        globalService.accessGlobalUserData().getFaculty() : 0))
                .retrieve()
                .bodyToFlux(Object.class)
                .collectList()
                .block();
    }

    @Override
    public Object getProgramsByFaculty(List<Integer> facultyId) {
        return webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .post()
                .uri(PROGRAMS_SERVICE + "/faculty")
                .bodyValue(facultyId)
                .retrieve()
                .bodyToFlux(Object.class)
                .collectList()
                .block();
    }

    @Override
    public Object getProgramByFaculty(Integer facultyId) {
        return webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .get()
                .uri(PROGRAMS_SERVICE + "/faculty-id/" + facultyId)
                .retrieve()
                .bodyToFlux(Object.class)
                .collectList()
                .block();
    }

    @Override
    public Object getTecnicalPrograms(Integer facultyId) {
        return webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .get()
                .uri(PROGRAMS_SERVICE + "/technical/faculty-id/" + facultyId)
                .retrieve()
                .bodyToFlux(Object.class)
                .collectList()
                .block();
    }

    @Override
    public String createProposal(ProgramRequestModel programRequestModel) {
        programRequestModel.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        programRequestModel.setRoleId(globalService.accessGlobalUserData().getRole());
        return webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .post()
                .uri(PROGRAMS_SERVICE + "/proposal")
                .bodyValue(programRequestModel)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public String updateProposal(ProgramUpdateModel ProgramUpdateModel, Integer proposalId) {
        ProgramUpdateModel.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        ProgramUpdateModel.setRoleId(globalService.accessGlobalUserData().getRole());
        return webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .put()
                .uri(PROGRAMS_SERVICE + "/proposal/" + proposalId)
                .bodyValue(ProgramUpdateModel)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Object getProposal(Map<String, Object> params) {
        RoleRequest roleRequest = RoleRequest.builder()
                .facultyId(globalService.accessGlobalUserData().getFaculty())
                .roleId(globalService.accessGlobalUserData().getRole())
                .createdBy(globalService.accessGlobalUserData().getEmail())
                .build();
        return webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .post()
                .uri(PROGRAMS_SERVICE + "/get/proposal" + converParamsToString(params))
                .bodyValue(roleRequest)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public Object getProgramByStatus(Map<String, Object> params) {
        RoleRequest roleRequest = RoleRequest.builder()
                .facultyId(globalService.accessGlobalUserData().getFaculty())
                .roleId(globalService.accessGlobalUserData().getRole())
                .createdBy(globalService.accessGlobalUserData().getEmail())
                .build();
        return webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .post()
                .uri(PROGRAMS_SERVICE + "/by-status" + converParamsToString(params))
                .bodyValue(roleRequest)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public Object getProgramById(Integer programId) {
        return webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .get()
                .uri(PROGRAMS_SERVICE + "/" + programId)
                .retrieve()
                .bodyToMono(ProgramIdDto.class)
                .block();
    }

    @Override
    public Object getProposalHistoric(Integer proposalId) {
        return webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .get()
                .uri(PROGRAMS_SERVICE + "/historic/" + proposalId)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public boolean getIsEnlarge(Integer programId) {
        return Boolean.TRUE.equals(webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .get()
                .uri(PROGRAMS_SERVICE + "/" + programId + "/is-formal")
                .retrieve()
                .bodyToMono(boolean.class)
                .block());
    }

    @Override
    public Integer getUpdateStatus(Integer programId) {
        return webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .get()
                .uri(PROGRAMS_SERVICE + "/" + programId + "/upgrade-status")
                .retrieve()
                .bodyToMono(Integer.class)
                .block();
    }

    @Override
    public Date getApprovedProposalDate(Integer programId) {
        return webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .get()
                .uri(PROGRAMS_SERVICE + "/" + programId + "/approved-date")
                .retrieve()
                .bodyToMono(Date.class)
                .block();
    }

    @Override
    public Object getProgramAcademic(QueryFilterProposal queryFilterProposal) {
        return webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .post()
                .uri(PROGRAMS_SERVICE + "/own")
                .bodyValue(queryFilterProposal)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    private String converParamsToString(Map<String, Object> params) {
        StringBuilder paramsValue = new StringBuilder("?");
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            paramsValue.append(entry.getKey()).append("=").append(entry.getValue().toString()).append("&");
        }
        return paramsValue.substring(0, paramsValue.toString().length() - 1);
    }

}
