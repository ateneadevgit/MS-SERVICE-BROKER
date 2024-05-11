package com.fusm.servicebroker.servicebroker.controller.ms_catalog;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_catalog.CatalogItemModel;
import com.fusm.servicebroker.servicebroker.model.ms_catalog.CatalogItemRequestModel;
import com.fusm.servicebroker.servicebroker.model.ms_catalog.SearchCatalogItem;
import com.fusm.servicebroker.servicebroker.service.ms_catalog.ICatalogItemService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * Esta clase expone todos los servicios relacionados con los catalog item
 * ITSense Inc - Andrea Gómez
 */

@Api(value = "ms-catalogs: Catalog item controller")
@RestController
@RequestMapping(value = AppRoutes.PUBLIC_ROUTE + AppRoutes.CATALOG_ITEM_ROUTE)
public class CatalogItemController {

    @Autowired
    private ICatalogItemService catalogItemService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Crea un catalog item
     * @param catalogItemRequestModel Modelo para crear un catalog item
     * @return OK
     */
    @PostMapping
    @ApiOperation(
            value = "Create catalog item"
    )
    public ResponseEntity<Response<String>> createCatalogItem(
            @RequestBody CatalogItemRequestModel catalogItemRequestModel,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(catalogItemRequestModel.toString(), Constant.POST_METHOD, false, Constant.CATALOG_MODULE, authorizationHeader);
        catalogItemService.createCatalogItem(catalogItemRequestModel);
        return new ResponseEntity<>(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase()),
                HttpStatus.OK
        );
    }

    /**
     * Obtiene los catalogs item segun el id del catálogo al que pertenecen de forma organizada
     * @param catalogId ID del catálogo
     * @return Lista de catalog item
     */
    @GetMapping("/catalog-id/{catalog-id}")
    @ApiOperation(
            value = "Get all catalogs items by a given catalog"
    )
    public ResponseEntity<Response<Object>> getCatalogsItemsByCatalog(
            @PathVariable("catalog-id") Integer catalogId
    ) {

        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, catalogItemService.getCatalogItemsByCatalog(catalogId))
        );
    }

    /**
     * los catalogs item segun el id del catálogo al que pertenecen
     * @param catalogId ID del catálogo
     * @return Lista de catalog item
     */
    @GetMapping("/all/catalog-id/{catalog-id}")
    public ResponseEntity<Response<Object>> getAllCatalogsItemsByCatalog(
            @PathVariable("catalog-id") Integer catalogId
    ) {
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, catalogItemService.getAllCatalogItemsByCatalog(catalogId))
        );
    }

    /**
     * Obtiene el catalog item según su ID
     * @param catalogItemId ID del catalog item
     * @return catalog item
     */
    @GetMapping("/{id}")
    @ApiOperation(
            value = "Get catalog item by id"
    )
    public ResponseEntity<Response<Object>> getCatalogItemById(
            @PathVariable("id") Integer catalogItemId
    ) {
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, catalogItemService.getCatalogItemsById(catalogItemId))
        );
    }

    /**
     * Actualizar catalog item
     * @param catalogItemId ID del catalog item
     * @param catalogItemRequestModel Modelo de catalog item que contiene los campos a actualizar el catalog item
     * @return OK
     */
    @PutMapping("/{id}")
    @ApiOperation(
            value = "Update catalog item"
    )
    public ResponseEntity<Response<String>> updateCatalogItem(
            @PathVariable("id") Integer catalogItemId,
            @RequestBody CatalogItemRequestModel catalogItemRequestModel,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(catalogItemRequestModel.toString(), Constant.PUT_METHOD, false, Constant.CATALOG_MODULE, authorizationHeader);
        catalogItemService.updateCatalogItem(catalogItemRequestModel, catalogItemId);
        return new ResponseEntity<>(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase()),
                HttpStatus.OK
        );
    }

    /**
     * Deshabilita un catalog item
     * @param catalogItemId ID del catalog item
     * @return OK
     */
    @DeleteMapping("/{id}")
    @ApiOperation(
            value = "Delete catalog item"
    )
    public ResponseEntity<Response<String>> disableCatalogItem(
            @PathVariable("id") Integer catalogItemId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.DELETE_METHOD, false, Constant.CATALOG_MODULE, authorizationHeader);
        catalogItemService.disableCatalogItem(catalogItemId);
        return new ResponseEntity<>(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase()),
                HttpStatus.OK
        );
    }

    /**
     * Habilita y deshabilita el catalog item
     * @param catalogItemId ID del catalog item
     * @param enabled True o False en caso que se quiera habilitar o deshabilitar el catalog item
     * @return OK
     */
    @DeleteMapping("/{id}/enabled/{enabled}")
    public ResponseEntity<Response<String>> enableDisableCatalogItem(
            @PathVariable("id") Integer catalogItemId,
            @PathVariable("enabled") Boolean enabled,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.DELETE_METHOD, false, Constant.CATALOG_MODULE, authorizationHeader);
        catalogItemService.enableDisableCatalogItem(catalogItemId, enabled);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Obtiene una lista de catalog item segpun su respectivo catálogo con la posibilidad de filtrar
     * @param catalogId ID del catálogo
     * @param searchCatalogItem Parámetros de búsqueda para realizar el filtro
     * @return lista de catalog item
     */
    @PostMapping("/catalog-id/{catalog-id}/filter")
    public ResponseEntity<Response<Object>> getCatalogItemsByCatalogWithFilter(
            @PathVariable("catalog-id") Integer catalogId,
            @RequestBody SearchCatalogItem searchCatalogItem
    ) {
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, catalogItemService.getCatalogItemsByCatalogWithFilter(searchCatalogItem, catalogId))
        );
    }

}
