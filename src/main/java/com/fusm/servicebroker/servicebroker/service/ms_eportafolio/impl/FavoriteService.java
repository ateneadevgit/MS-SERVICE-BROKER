package com.fusm.servicebroker.servicebroker.service.ms_eportafolio.impl;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.PageModel;
import com.fusm.servicebroker.servicebroker.model.ms_eportafolio.FavoriteRequest;
import com.fusm.servicebroker.servicebroker.model.ms_eportafolio.FileSearch;
import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.service.ms_eportafolio.IFavoriteService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService implements IFavoriteService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-eportafolio.complete-path}")
    private String EPORTAFOLIO_ROUTE;

    @Value("${ms-eportafolio.path}")
    private String EPORTAFOLIO_SERVICE;

    @Autowired
    private GlobalService globalService;


    @Override
    public Object getFavoritesFiles(FileSearch fileSearch, PageModel pageModel) {
        fileSearch.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        fileSearch.setFacultyId(globalService.accessGlobalUserData().getFaculty());
        return webClientConnector.connectWebClient(EPORTAFOLIO_ROUTE)
                .post()
                .uri(EPORTAFOLIO_SERVICE + "/file/favorite?page=" + pageModel.getPage() + "&size=" + pageModel.getSize())
                .bodyValue(fileSearch)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public void addFavorite(FavoriteRequest favoriteRequest, Integer fileId) {
        favoriteRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        webClientConnector.connectWebClient(EPORTAFOLIO_ROUTE)
                .post()
                .uri(EPORTAFOLIO_SERVICE + "/file/" + fileId + "/favorite/add")
                .bodyValue(favoriteRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
