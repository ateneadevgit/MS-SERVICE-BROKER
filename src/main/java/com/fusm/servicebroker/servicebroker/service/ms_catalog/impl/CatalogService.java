package com.fusm.servicebroker.servicebroker.service.ms_catalog.impl;

import com.fusm.servicebroker.servicebroker.model.ms_catalog.CatalogRequestModel;
import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.service.ms_catalog.ICatalogService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class CatalogService implements ICatalogService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-catalogs.complete-path}")
    private String CATALOGS_ROUTE;

    @Value("${ms-catalogs.catalogs.path}")
    private String CATALOG_SERVICE;

    @Autowired
    private GlobalService globalService;


    @Override
    public void createCatalog(CatalogRequestModel catalogRequestModel) {
        catalogRequestModel.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        webClientConnector.connectWebClient(CATALOGS_ROUTE)
                .post()
                .uri(CATALOG_SERVICE)
                .bodyValue(catalogRequestModel)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Object getCatalogs() {
        return webClientConnector.connectWebClient(CATALOGS_ROUTE)
                .get()
                .uri(CATALOG_SERVICE)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public Object getCatalogById(Integer catalogId) {
        return webClientConnector.connectWebClient(CATALOGS_ROUTE)
                .get()
                .uri(CATALOG_SERVICE + "/" + catalogId)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public void updateCatalog(CatalogRequestModel catalogRequestModel, Integer catalogId) {
        catalogRequestModel.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        webClientConnector.connectWebClient(CATALOGS_ROUTE)
                .put()
                .uri(CATALOG_SERVICE + "/" + catalogId)
                .bodyValue(catalogRequestModel)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void disableCatalog(Integer catalogId) {
        webClientConnector.connectWebClient(CATALOGS_ROUTE)
                .delete()
                .uri(CATALOG_SERVICE + "/" + catalogId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
