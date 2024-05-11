package com.fusm.servicebroker.servicebroker.controller.ms_eportafolio;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.PageModel;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_eportafolio.FavoriteRequest;
import com.fusm.servicebroker.servicebroker.model.ms_eportafolio.FileSearch;
import com.fusm.servicebroker.servicebroker.service.ms_eportafolio.IFavoriteService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Clase que expone los servicios relacionados con los favoritos del e-portafolio
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value =  AppRoutes.PUBLIC_ROUTE + AppRoutes.EPORTAFOLIO_ROUTE + "/file")
public class FavoriteController {

    @Autowired
    private IFavoriteService favoriteService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Obtiene los archivos en favorito
     * @param fileSearch Modelo que contiene los parámetros de búsqueda para hacer el filtrado
     * @param params parámetros como page y size para la paginación de la respuesta
     * @return lista de archivos
     */
    @PostMapping("/favorite")
    private ResponseEntity<Response<Object>> getFavoritesFiles(
            @RequestBody FileSearch fileSearch,
            @RequestParam Map<String, Object> params,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.EPORTAFOLIO_MODULE, authorizationHeader);
        int page = params.get("page") != null ? Integer.parseInt(params.get("page").toString()) : 1;
        int size = params.get("size") != null ? Integer.parseInt(params.get("size").toString()) : 10;
        return ResponseEntity.ok(
                new Response<>(
                        HttpStatus.OK,
                        favoriteService.getFavoritesFiles(
                                fileSearch,
                                PageModel.builder()
                                        .page(page)
                                        .size(size)
                                        .build())
                )
        );
    }

    /**
     * Agregar un archivo a favorito
     * @param fileId ID del archivo
     * @param favoriteRequest Modelo que tiene la información del archivo
     * @return OK
     */
    @PostMapping("/{fileId}/favorite/add")
    private ResponseEntity<Response<String>> addFileToFavorite(
            @PathVariable("fileId") Integer fileId,
            @RequestBody FavoriteRequest favoriteRequest,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(favoriteRequest.toString(), Constant.POST_METHOD, false, Constant.EPORTAFOLIO_MODULE, authorizationHeader);
        favoriteService.addFavorite(favoriteRequest, fileId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

}

