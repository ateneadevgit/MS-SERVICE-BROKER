package com.fusm.servicebroker.servicebroker.service.ms_safety_mesh.impl;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.ms_safety_mesh.PermissionRequest;
import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.service.ms_safety_mesh.IPermissionService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PermissionService implements IPermissionService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-safety-mesh.complete-path}")
    private String PERMISSION_ROUTE;

    @Value("${ms-safety-mesh.permission.path}")
    private String PERMISSION_SERVICE;

    @Autowired
    private GlobalService globalService;


    @Override
    public Object getPermissionByRole(Integer roleId) {
        return webClientConnector.connectWebClient(PERMISSION_ROUTE)
                .get()
                .uri(PERMISSION_SERVICE + "/by-role/" + roleId)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public void createPermission(PermissionRequest permissionRequest) {
        permissionRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        webClientConnector.connectWebClient(PERMISSION_ROUTE)
                .post()
                .uri(PERMISSION_SERVICE)
                .bodyValue(permissionRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void updatePermission(PermissionRequest permissionRequest) {
        webClientConnector.connectWebClient(PERMISSION_ROUTE)
                .put()
                .uri(PERMISSION_SERVICE)
                .bodyValue(permissionRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Object getModuleByRole(Integer roleId) {
        return webClientConnector.connectWebClient(PERMISSION_ROUTE)
                .get()
                .uri(PERMISSION_SERVICE + "/module/by-role/" + roleId)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public Object getFloatingModuleByRole(Integer roleId) {
        return webClientConnector.connectWebClient(PERMISSION_ROUTE)
                .get()
                .uri(PERMISSION_SERVICE + "/floating/module/by-role/" + roleId)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public Object getPermissionsByModule(Integer moduleId) {
        return webClientConnector.connectWebClient(PERMISSION_ROUTE)
                .get()
                .uri(PERMISSION_SERVICE + "/module-id/" + moduleId)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

}
