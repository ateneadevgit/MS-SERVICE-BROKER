package com.fusm.servicebroker.servicebroker.service.ms_catalog;

import com.fusm.servicebroker.servicebroker.model.ms_catalog.CatalogModel;
import com.fusm.servicebroker.servicebroker.model.ms_catalog.CatalogRequestModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICatalogService {
    void createCatalog(CatalogRequestModel catalogRequestModel);
    Object getCatalogs();
    Object getCatalogById(Integer catalogId);
    void updateCatalog(CatalogRequestModel catalogRequestModel, Integer catalogId);
    void disableCatalog(Integer catalogId);
}
