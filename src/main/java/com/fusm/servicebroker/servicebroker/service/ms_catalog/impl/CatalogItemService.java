package com.fusm.servicebroker.servicebroker.service.ms_catalog.impl;

import com.fusm.servicebroker.servicebroker.model.ms_catalog.CatalogItemModel;
import com.fusm.servicebroker.servicebroker.model.ms_catalog.CatalogItemRequestModel;
import com.fusm.servicebroker.servicebroker.model.ms_catalog.SearchCatalogItem;
import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.service.ms_catalog.ICatalogItemService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogItemService implements ICatalogItemService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-catalogs.complete-path}")
    private String CATALOG_ITEM_ROUTE;

    @Value("${ms-catalogs.catalog-item.path}")
    private String CATALOG_ITEM_SERVICE;

    @Autowired
    private GlobalService globalService;


    @Override
    public void createCatalogItem(CatalogItemRequestModel catalogItemRequestModel) {
        catalogItemRequestModel.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        webClientConnector.connectWebClient(CATALOG_ITEM_ROUTE)
                .post()
                .uri(CATALOG_ITEM_SERVICE)
                .bodyValue(catalogItemRequestModel)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Object getCatalogItemsByCatalog(Integer catalogId) {
        return webClientConnector.connectWebClient(CATALOG_ITEM_ROUTE)
                .get()
                .uri(CATALOG_ITEM_SERVICE + "/catalog-id/" + catalogId)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public Object getAllCatalogItemsByCatalog(Integer catalogId) {
        return webClientConnector.connectWebClient(CATALOG_ITEM_ROUTE)
                .get()
                .uri(CATALOG_ITEM_SERVICE + "/all/catalog-id/" + catalogId)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public Object getCatalogItemsById(Integer catalogItemId) {
        return webClientConnector.connectWebClient(CATALOG_ITEM_ROUTE)
                .get()
                .uri(CATALOG_ITEM_SERVICE + "/" + catalogItemId)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public void updateCatalogItem(CatalogItemRequestModel catalogItemRequestModel, Integer catalogItemId) {
        catalogItemRequestModel.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        webClientConnector.connectWebClient(CATALOG_ITEM_ROUTE)
                .put()
                .uri(CATALOG_ITEM_SERVICE + "/" + catalogItemId)
                .bodyValue(catalogItemRequestModel)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void disableCatalogItem(Integer catalogItemId) {
        webClientConnector.connectWebClient(CATALOG_ITEM_ROUTE)
                .delete()
                .uri(CATALOG_ITEM_SERVICE + "/" + catalogItemId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void enableDisableCatalogItem(Integer catalogItemId, Boolean enabled) {
        webClientConnector.connectWebClient(CATALOG_ITEM_ROUTE)
                .delete()
                .uri(CATALOG_ITEM_SERVICE + "/" + catalogItemId + "/enabled/" + enabled)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Object getCatalogItemsByCatalogWithFilter(SearchCatalogItem searchCatalogItem, Integer catalogId) {
        return webClientConnector.connectWebClient(CATALOG_ITEM_ROUTE)
                .post()
                .uri(CATALOG_ITEM_SERVICE + "/catalog-id/" + catalogId + "/filter")
                .bodyValue(searchCatalogItem)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }
}
