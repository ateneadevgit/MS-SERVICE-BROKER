package com.fusm.servicebroker.servicebroker.controller.ms_workflow;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.IntegrativeActivityRequest;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.IIntegrativeActivityService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Clase que contiene toda la lógica relacionada con las actividades integradoras
 * ITSense Inc - Andrea Gómez
 */


@RestController
@RequestMapping(value =  AppRoutes.PUBLIC_ROUTE + AppRoutes.WORKFLOW_ROUTE + "/activity")
public class IntegrativeActivityController {

    @Autowired
    private IIntegrativeActivityService integrativeActivityService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Actualizar actividad integradora
     * @param activityRequest Modelo que contiene la información para actualizar una actividad integradora
     * @param activityId ID de la actividad
     * @return OK
     */
    @PutMapping("/{id}")
    private ResponseEntity<Response<String>> updateActivity(
            @RequestBody IntegrativeActivityRequest activityRequest,
            @PathVariable("id") Integer activityId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(activityRequest.toString(), Constant.PUT_METHOD, false, Constant.NIFS_MODULE, authorizationHeader);
        integrativeActivityService.updateActivity(activityRequest, activityId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Dehabilita una actividad integradora
     * @param activityId ID de la actividad
     * @return OK
     */
    @DeleteMapping("/{id}")
    private ResponseEntity<Response<String>> disableActivity(
            @PathVariable("id") Integer activityId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.DELETE_METHOD, false, Constant.NIFS_MODULE, authorizationHeader);
        integrativeActivityService.disableActivity(activityId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Crea una actividad integradora
     * @param integrativeActivityRequests Modelo que contiene la información para crear una actividad integradora
     * @param curriculumId ID del nivel
     * @return OK
     */
    @PostMapping("/curriculum-id/{id}")
    private ResponseEntity<Response<String>> createActivity(
            @RequestBody List<IntegrativeActivityRequest> integrativeActivityRequests,
            @PathVariable("id") Integer curriculumId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(integrativeActivityRequests.toString(), Constant.POST_METHOD, false, Constant.NIFS_MODULE, authorizationHeader);
        integrativeActivityService.createActivity(integrativeActivityRequests, curriculumId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

}
