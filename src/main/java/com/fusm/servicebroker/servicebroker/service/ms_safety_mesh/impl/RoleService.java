package com.fusm.servicebroker.servicebroker.service.ms_safety_mesh.impl;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.ms_safety_mesh.RoleRequest;
import com.fusm.servicebroker.servicebroker.model.ms_security.BlackListModel;
import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.service.ms_safety_mesh.IRoleService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-safety-mesh.complete-path}")
    private String ROLE_ROUTE;

    @Value("${ms-safety-mesh.role.path}")
    private String ROLE_SERVICE;

    @Autowired
    private GlobalService globalService;


    @Override
    public Object getRoles() {
        return webClientConnector.connectWebClient(ROLE_ROUTE)
                .get()
                .uri(ROLE_SERVICE)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public String createRole(RoleRequest roleRequest) {
        roleRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        return webClientConnector.connectWebClient(ROLE_ROUTE)
                .post()
                .uri(ROLE_SERVICE)
                .bodyValue(roleRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public String updateRole(RoleRequest roleRequest, Integer roleId) {
        return webClientConnector.connectWebClient(ROLE_ROUTE)
                .put()
                .uri(ROLE_SERVICE + "/" + roleId)
                .bodyValue(roleRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void enableDisableRole(Integer roleId, Boolean enabled) {
        webClientConnector.connectWebClient(ROLE_ROUTE)
                .delete()
                .uri(ROLE_SERVICE + "/" + roleId + "/enabled/" + enabled)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Object getRolesWithNotPermissionInModule(Integer moduleId) {
        return webClientConnector.connectWebClient(ROLE_ROUTE)
                .get()
                .uri(ROLE_SERVICE + "/not-included/module-id/" + moduleId)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }
}
