package com.fusm.servicebroker.servicebroker.controller.ms_workflow;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.EvaluateGuide;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.RenovationSubjectGuideRequest;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.IRenovationSubjectGuideService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Clase que expone los servicios relacionados con la renovación de una guia de asignatura
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.PUBLIC_ROUTE + AppRoutes.WORKFLOW_ROUTE + "/guide")
public class RenovationSubjectGuideController {

    @Autowired
    private IRenovationSubjectGuideService renovationSubjectGuideService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Crea la renovación de una guía de asignatura
     * @param renovationSubjectGuideRequest Modelo que contiene la información para crear una guia
     * @param subjectGuideId ID guía de asignatura
     * @return OK
     */
    @PostMapping("/{id}/renovation")
    private ResponseEntity<Response<String>> createRenovationSubjectGuide(
            @RequestBody RenovationSubjectGuideRequest renovationSubjectGuideRequest,
            @PathVariable("id") Integer subjectGuideId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(renovationSubjectGuideRequest.toString(), Constant.POST_METHOD, false, Constant.SIGNATURE_PROGRAM_MODULE, authorizationHeader);
        renovationSubjectGuideService.createRenovationSubjectGuide(renovationSubjectGuideRequest, subjectGuideId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Evaluar una guía de asignatura
     * @param evaluateGuide Modelo que permite la evaluación de la guía
     * @param renovationId ID de la renovación
     * @return OK
     */
    @PostMapping("/renovation/{id}")
    private ResponseEntity<Response<String>> evaluateRenovationSubjectGuide(
            @RequestBody EvaluateGuide evaluateGuide,
            @PathVariable("id") Integer renovationId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(evaluateGuide.toString(), Constant.POST_METHOD, false, Constant.SIGNATURE_PROGRAM_MODULE, authorizationHeader);
        renovationSubjectGuideService.evaluateRenovationSubjectGuide(evaluateGuide, renovationId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Obtener las renovaciones de una guía de asignatura
     * @param userData Modelo que contiene la información de un usuario
     * @param subjectGuideId ID de la guía
     * @return lista de renovaciones
     */
    @GetMapping("/{id}/renovation")
    private ResponseEntity<Response<Object>> getRenovationsBySubjectGuide(
            @PathVariable("id") Integer subjectGuideId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.SIGNATURE_PROGRAM_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, renovationSubjectGuideService.getRenovationsBySubjectGuide(subjectGuideId))
        );
    }

}
