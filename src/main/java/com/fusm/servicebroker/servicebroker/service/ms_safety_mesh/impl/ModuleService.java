package com.fusm.servicebroker.servicebroker.service.ms_safety_mesh.impl;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.ms_safety_mesh.ModuleRequest;
import com.fusm.servicebroker.servicebroker.model.ms_security.BlackListModel;
import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.service.ms_safety_mesh.IModuleService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ModuleService implements IModuleService {

    @Autowired
    private IMiddlewareService middlewareService;

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-safety-mesh.complete-path}")
    private String MODULE_ROUTE;

    @Value("${ms-safety-mesh.module.path}")
    private String MODULE_SERVICE;

    @Autowired
    private GlobalService globalService;


    @Override
    public Object getModules() {
        return webClientConnector.connectWebClient(MODULE_ROUTE)
                .get()
                .uri(MODULE_SERVICE)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public String createModule(ModuleRequest moduleRequest) {
        moduleRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        return webClientConnector.connectWebClient(MODULE_ROUTE)
                .post()
                .uri(MODULE_SERVICE)
                .bodyValue(moduleRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public String updateModule(ModuleRequest moduleRequest, Integer moduleId) {
        return webClientConnector.connectWebClient(MODULE_ROUTE)
                .put()
                .uri(MODULE_SERVICE + "/" + moduleId)
                .bodyValue(moduleRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
