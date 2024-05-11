package com.fusm.servicebroker.servicebroker.service.ms_program.impl;

import com.fusm.servicebroker.servicebroker.service.ms_program.IProgramModuleService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramModuleService implements IProgramModuleService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-program.complete-path}")
    private String PROGRAM_ROUTE;

    @Value("${ms-program.programs.path}")
    private String PROGRAMS_SERVICE;


    @Override
    public Object getProgramModules(Boolean isEnlarge) {
        return webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .get()
                .uri(PROGRAMS_SERVICE + "/module?isEnlarge=" + isEnlarge)
                .retrieve()
                .bodyToFlux(Object.class)
                .collectList()
                .block();
    }

    @Override
    public Object getAllProgramModules(Boolean isEnlarge) {
        return webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .get()
                .uri(PROGRAMS_SERVICE + "/module/no-editable?isEnlarge=" + isEnlarge)
                .retrieve()
                .bodyToFlux(Object.class)
                .collectList()
                .block();
    }

}
