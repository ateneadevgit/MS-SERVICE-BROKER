package com.fusm.servicebroker.servicebroker.controller.ms_catalog;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_catalog.CatalogRequestModel;
import com.fusm.servicebroker.servicebroker.service.ms_catalog.ICatalogService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Clase que expone todos los servicios relacionados con los catálogos
 * ITSense Inc - Andrea Gómez
 */

@Api(value = "ms-catalogs: Catalog controller")
@RestController
@RequestMapping(value = AppRoutes.PUBLIC_ROUTE + AppRoutes.CATALOG_ROUTE)
public class CatalogController {

    @Autowired
    private ICatalogService catalogService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Obtiene todos los catalogos
     * @return lista de catálogos
     */
    @GetMapping
    @ApiOperation(
            value = "Get all catalogs"
    )
    public ResponseEntity<Response<Object>> getCatalogs() {
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, catalogService.getCatalogs())
        );
    }

    /**
     * Obtener el catalogo segun su ID
     * @param catalogId ID del catalogo
     * @return Catalogo
     */
    @GetMapping("/{id}")
    @ApiOperation(
            value = "Get catalog by id"
    )
    public ResponseEntity<Response<Object>> getCatalogById(
            @PathVariable("id") Integer catalogId
    ) {
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, catalogService.getCatalogById(catalogId))
        );
    }

    /**
     * Crear un catalogo
     * @param catalogRequestModel Modelo para la creación del catálogo
     * @return OK
     */
    @PostMapping
    @ApiOperation(
            value = "Creation of catalogs"
    )
    public ResponseEntity<Response<String>> createCatalog(
            @Valid @RequestBody CatalogRequestModel catalogRequestModel,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(catalogRequestModel.toString(), Constant.POST_METHOD, false, Constant.CATALOG_MODULE, authorizationHeader);
        catalogService.createCatalog(catalogRequestModel);
        return new ResponseEntity<>(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase()),
                HttpStatus.OK
        );
    }

    /**
     * Actualiza el catálogo
     * @param catalogId ID del catálogo
     * @param catalogRequestModel Modelo con la información a actualizar del catálogo
     * @return OK
     */
    @PutMapping("/{id}")
    @ApiOperation(
            value = "Update catalog"
    )
    public ResponseEntity<Response<String>> updateCatalog(
            @PathVariable("id") Integer catalogId,
            @Valid @RequestBody CatalogRequestModel catalogRequestModel,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(catalogRequestModel.toString(), Constant.PUT_METHOD, false, Constant.CATALOG_MODULE, authorizationHeader);
        catalogService.updateCatalog(catalogRequestModel, catalogId);
        return new ResponseEntity<>(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase()),
                HttpStatus.OK
        );
    }

    /**
     * Deshabilita un catálogo
     * @param catalogId ID del catálogo
     * @return OK
     */
    @DeleteMapping("/{id}")
    @ApiOperation(
            value = "Delete catalog"
    )
    public ResponseEntity<Response<String>> deleteCatalog(
            @PathVariable("id") Integer catalogId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.DELETE_METHOD, false, Constant.CATALOG_MODULE, authorizationHeader);
        catalogService.disableCatalog(catalogId);
        return new ResponseEntity<>(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase()),
                HttpStatus.OK
        );
    }

}
