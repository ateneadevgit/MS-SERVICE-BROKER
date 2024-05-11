package com.fusm.servicebroker.servicebroker.service.ms_sinu.impl;

import com.fusm.servicebroker.servicebroker.model.ms_sinu.SearchDirectory;
import com.fusm.servicebroker.servicebroker.model.ms_sinu.SearchRoles;
import com.fusm.servicebroker.servicebroker.service.ms_sinu.ISinuService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SinuService implements ISinuService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-sinu.complete-path}")
    private String SINU_ROUTE;

    @Value("${ms-sinu.path}")
    private String SINU_SERVICE;


    @Override
    public Object getUsersByRole(Integer roleId) {
        return webClientConnector.connectWebClient(SINU_ROUTE)
                .get()
                .uri(SINU_SERVICE + "/user/by-role/" + roleId)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public Object getUserByRolesIds(SearchRoles roles) {
        return webClientConnector.connectWebClient(SINU_ROUTE)
                .post()
                .uri(SINU_SERVICE + "/user/by-role")
                .bodyValue(roles)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public Object getDirectory(SearchDirectory searchDirectory) {
        return webClientConnector.connectWebClient(SINU_ROUTE)
                .post()
                .uri(SINU_SERVICE + "/directory")
                .bodyValue(searchDirectory)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public Object getUserGender() {
        return webClientConnector.connectWebClient(SINU_ROUTE)
                .get()
                .uri(SINU_SERVICE + "/user/by-gender")
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public Integer getStudentsByFaculty(Integer facultyId) {
        return webClientConnector.connectWebClient(SINU_ROUTE)
                .get()
                .uri(SINU_SERVICE + "/user/by-faculty/" + facultyId)
                .retrieve()
                .bodyToMono(Integer.class)
                .block();
    }
}
