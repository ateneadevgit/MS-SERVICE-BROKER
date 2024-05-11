package com.fusm.servicebroker.servicebroker.service.ms_eportafolio.impl;

import com.fusm.servicebroker.servicebroker.model.PageModel;
import com.fusm.servicebroker.servicebroker.model.ms_eportafolio.FileRequest;
import com.fusm.servicebroker.servicebroker.model.ms_eportafolio.FileSearch;
import com.fusm.servicebroker.servicebroker.model.ms_program.FileModel;
import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.service.ms_eportafolio.IFileService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FileService implements IFileService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-eportafolio.complete-path}")
    private String EPORTAFOLIO_ROUTE;

    @Value("${ms-eportafolio.path}")
    private String EPORTAFOLIO_SERVICE;

    @Autowired
    private GlobalService globalService;


    @Override
    public Object getFilesByFolder(FileSearch fileSearch, PageModel pageModel, Integer folderId) {
        fileSearch.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        fileSearch.setFacultyId(globalService.accessGlobalUserData().getFaculty());
        return webClientConnector.connectWebClient(EPORTAFOLIO_ROUTE)
                .post()
                .uri(EPORTAFOLIO_SERVICE + "/file/folder-id/" + folderId + "?page=" + pageModel.getPage() + "&size=" + pageModel.getSize())
                .bodyValue(fileSearch)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public Object getFileById(Integer fileId) {
        return webClientConnector.connectWebClient(EPORTAFOLIO_ROUTE)
                .get()
                .uri(EPORTAFOLIO_SERVICE + "/file/" + fileId)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public void createFile(FileRequest<FileModel> fileRequest) {
        fileRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        fileRequest.setFacultyId(globalService.accessGlobalUserData().getFaculty());
        webClientConnector.connectWebClient(EPORTAFOLIO_ROUTE)
                .post()
                .uri(EPORTAFOLIO_SERVICE + "/file")
                .bodyValue(fileRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void deleteFile(Integer fileId) {
        webClientConnector.connectWebClient(EPORTAFOLIO_ROUTE)
                .delete()
                .uri(EPORTAFOLIO_SERVICE + "/file/" + fileId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void updateFile(FileRequest<FileModel> fileRequest, Integer fileId) {
        fileRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        fileRequest.setFacultyId(globalService.accessGlobalUserData().getFaculty());
        webClientConnector.connectWebClient(EPORTAFOLIO_ROUTE)
                .put()
                .uri(EPORTAFOLIO_SERVICE + "/file/" + fileId)
                .bodyValue(fileRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Object getConsumedSpace() {
        return webClientConnector.connectWebClient(EPORTAFOLIO_ROUTE)
                .get()
                .uri(EPORTAFOLIO_SERVICE + "/file/consumed/user-id/" + globalService.accessGlobalUserData().getEmail())
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

}
