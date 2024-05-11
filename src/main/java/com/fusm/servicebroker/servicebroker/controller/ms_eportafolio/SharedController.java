package com.fusm.servicebroker.servicebroker.controller.ms_eportafolio;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.PageModel;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_eportafolio.FileSearch;
import com.fusm.servicebroker.servicebroker.service.ms_eportafolio.ISharedService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Clase que expone los servicios relacionados con los archivos compartidos del e-portafolio
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(AppRoutes.PUBLIC_ROUTE + AppRoutes.EPORTAFOLIO_ROUTE + "/file")
public class SharedController {

    @Autowired
    private ISharedService sharedService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Obtiene los archivos compartidos con un usuario
     * @param fileSearch Modelo que contiene los parámetros necesarios para filtrar
     * @param params Parámetros como page y size que sirven para la paginación de la respuesta
     * @return archivo
     */
    @PostMapping("/shared")
    private ResponseEntity<Response<Object>> getSharedFiles(
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
                        sharedService.getSharedFiles(
                                fileSearch,
                                PageModel.builder()
                                        .page(page)
                                        .size(size)
                                        .build()
                        )
                )
        );
    }

}
