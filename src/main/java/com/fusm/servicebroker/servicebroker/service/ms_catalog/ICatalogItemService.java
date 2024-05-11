package com.fusm.servicebroker.servicebroker.service.ms_catalog;

import com.fusm.servicebroker.servicebroker.model.ms_catalog.CatalogItemModel;
import com.fusm.servicebroker.servicebroker.model.ms_catalog.CatalogItemRequestModel;
import com.fusm.servicebroker.servicebroker.model.ms_catalog.SearchCatalogItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICatalogItemService {
    void createCatalogItem(CatalogItemRequestModel catalogItemRequestModel);
    Object getCatalogItemsByCatalog(Integer catalogId);
    Object getAllCatalogItemsByCatalog(Integer catalogId);
    Object getCatalogItemsById(Integer catalogItemId);
    void updateCatalogItem(CatalogItemRequestModel catalogItemRequestModel, Integer catalogItemId);
    void disableCatalogItem(Integer catalogItemId);
    void enableDisableCatalogItem(Integer catalogItemId, Boolean enabled);
    Object getCatalogItemsByCatalogWithFilter(SearchCatalogItem searchCatalogItem, Integer catalogId);
}
