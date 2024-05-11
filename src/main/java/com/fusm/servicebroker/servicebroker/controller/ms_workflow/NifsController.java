package com.fusm.servicebroker.servicebroker.controller.ms_workflow;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.NifsRequest;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.INifsService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Clase que expone los servicios relacionados con las NIF
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.PUBLIC_ROUTE + AppRoutes.WORKFLOW_ROUTE + "/nif")
public class NifsController {

    @Autowired
    private INifsService nifsService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Obtiene todos los NIFS por el tipo
     * @param type  ID del tipo
     * @return nif
     */
    @GetMapping("/type/{type}")
    private ResponseEntity<Response<Object>> getNif(
            @PathVariable("type") Integer type,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.NIFS_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, nifsService.viewNifsData(type))
        );
    }

    /**
     * Crea un nuevo NIF
     * @param nifsRequest Modelo que contiene la información para crear un nuevo NIF
     * @return OK
     */
    @PostMapping
    private ResponseEntity<Response<String>> createNif(
            @RequestBody NifsRequest nifsRequest,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(nifsRequest.toString(), Constant.POST_METHOD, false, Constant.NIFS_MODULE, authorizationHeader);
        nifsService.createNifsData(nifsRequest);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Agrega una nueva sección
     * @param requestList Modelo que contiene las secciones a agregar
     * @param sectionId ID sección
     * @return OK
     */
    @PostMapping("/{id}")
    private ResponseEntity<Response<String>> addSection(
            @RequestBody List<NifsRequest> requestList,
            @PathVariable("id") Integer sectionId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(requestList.toString(), Constant.POST_METHOD, false, Constant.NIFS_MODULE, authorizationHeader);
        nifsService.addSection(requestList, sectionId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Actualiza un NIF
     * @param nifsRequest Modelo que contiene la información a actualizar de un NIF
     * @param nifId ID del NIF
     * @return OK
     */
    @PutMapping("/{id}")
    private ResponseEntity<Response<String>> updateNif(
            @RequestBody NifsRequest nifsRequest,
            @PathVariable("id") Integer nifId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(nifsRequest.toString(), Constant.PUT_METHOD, false, Constant.NIFS_MODULE, authorizationHeader);
        nifsService.updateNifsData(nifsRequest, nifId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Deshabilita una sección
     * @param nifId ID del NIF
     * @return OK
     */
    @DeleteMapping("/{id}")
    private ResponseEntity<Response<String>> disableSection(
            @PathVariable("id") Integer nifId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.DELETE_METHOD, false, Constant.NIFS_MODULE, authorizationHeader);
        nifsService.disableSection(nifId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

}
