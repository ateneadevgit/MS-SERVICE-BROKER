package com.fusm.servicebroker.servicebroker.service.ms_eportafolio.impl;

import com.fusm.servicebroker.servicebroker.model.ms_eportafolio.FolderRequest;
import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.service.ms_eportafolio.IFolderService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FolderService implements IFolderService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-eportafolio.complete-path}")
    private String EPORTAFOLIO_ROUTE;

    @Value("${ms-eportafolio.path}")
    private String EPORTAFOLIO_SERVICE;

    @Autowired
    private GlobalService globalService;


    @Override
    public Object getFoldersByUser() {
        return webClientConnector.connectWebClient(EPORTAFOLIO_ROUTE)
                .get()
                .uri(EPORTAFOLIO_SERVICE + "/folder/user-id/" + globalService.accessGlobalUserData().getEmail())
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public void createFolder(FolderRequest folderRequest) {
        folderRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        webClientConnector.connectWebClient(EPORTAFOLIO_ROUTE)
                .post()
                .uri(EPORTAFOLIO_SERVICE + "/folder")
                .bodyValue(folderRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void updateFolder(FolderRequest folderRequest, Integer folderId) {
        folderRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        webClientConnector.connectWebClient(EPORTAFOLIO_ROUTE)
                .put()
                .uri(EPORTAFOLIO_SERVICE + "/folder/" + folderId)
                .bodyValue(folderRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void deleteFolder(Integer folderId) {
        webClientConnector.connectWebClient(EPORTAFOLIO_ROUTE)
                .delete()
                .uri(EPORTAFOLIO_SERVICE + "/folder/" + folderId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
