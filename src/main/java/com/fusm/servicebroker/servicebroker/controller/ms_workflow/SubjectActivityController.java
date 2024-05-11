package com.fusm.servicebroker.servicebroker.controller.ms_workflow;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.EvaluateGuide;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.PastActivityRequest;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.SearchTeacher;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.SubjectActivityRequest;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.ISubjectActivityService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * Clase que expone los servicios relacionados con las actividades de asignatura
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.PUBLIC_ROUTE + AppRoutes.WORKFLOW_ROUTE + "/guide")
public class SubjectActivityController {

    @Autowired
    private ISubjectActivityService subjectActivityService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Crea una actividad de asignatura
     * @param subjectActivityRequests Modelo que contiene la información para creación una actividad de asignatura
     * @param subjectguideId ID de la guía de asignatura
     * @return OK
     */
    @PostMapping("/{id}/activity")
    private ResponseEntity<Response<String>> createSubjectActivity(
            @RequestBody List<SubjectActivityRequest> subjectActivityRequests,
            @PathVariable("id") Integer subjectguideId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(subjectActivityRequests.toString(), Constant.POST_METHOD, false, Constant.SIGNATURE_PROGRAM_MODULE, authorizationHeader);
        subjectActivityService.createSubjectActivity(subjectActivityRequests, subjectguideId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Actualizar una actividad de asignatura
     * @param subjectActivityRequests Modelo que contiene la información a actualizar de una actividad de asignatura
     * @param subjectActivityId ID de la actividad
     * @return OK
     */
    @PutMapping("/activity/{id}")
    private ResponseEntity<Response<String>> updateSubjectActivity(
            @RequestBody SubjectActivityRequest subjectActivityRequests,
            @PathVariable("id") Integer subjectActivityId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(subjectActivityRequests.toString(), Constant.PUT_METHOD, false, Constant.SIGNATURE_PROGRAM_MODULE, authorizationHeader);
        subjectActivityService.updateSubjectActivity(subjectActivityRequests, subjectActivityId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Eliminar una actividad
     * @param subjectActivityId ID de la actividad
     * @return OK
     */
    @DeleteMapping("/activity/{id}")
    private ResponseEntity<Response<String>> deleteSubjectActivity(
            @PathVariable("id") Integer subjectActivityId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.DELETE_METHOD, false, Constant.SIGNATURE_PROGRAM_MODULE, authorizationHeader);
        subjectActivityService.deleteSubjectActivity(subjectActivityId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Evaluar una actividad de asignatura
     * @param evaluateGuide Modelo que contiene la información para evaluar la actividad
     * @param subjectguideId ID de la guia de asignatura
     * @return OK
     */
    @PostMapping("/{id}/activity/evaluate")
    private ResponseEntity<Response<String>> evaluateSubjectActivity(
            @RequestBody EvaluateGuide evaluateGuide,
            @PathVariable("id") Integer subjectguideId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(evaluateGuide.getCreatedBy(), Constant.POST_METHOD, false, Constant.SIGNATURE_PROGRAM_MODULE, authorizationHeader);
        subjectActivityService.evaluateSubjectActivity(evaluateGuide, subjectguideId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Obtener las actividades de semestres pasados según el docente
     * @param searchTeacher Modelo que contiene la información del docente
     * @param subjectGuideId ID de la guía de asignatura
     * @return lista de actividades
     */
    @PostMapping("/{id}/activity/past")
    private ResponseEntity<Response<Object>> getPastActivities(
            @RequestBody SearchTeacher searchTeacher,
            @PathVariable("id") Integer subjectGuideId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.SIGNATURE_PROGRAM_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, subjectActivityService.getPastActivities(searchTeacher, subjectGuideId))
        );
    }

    /**
     * Agregar actividad pasada al periodo actial
     * @param pastActivityRequests Lista de actividades pasadas a agregar
     * @return OK
     */
    @PostMapping("/activity/past")
    private ResponseEntity<Response<String>> addPastActivityToCurrentPeriod(
            @RequestBody List<PastActivityRequest> pastActivityRequests,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(pastActivityRequests.toString(), Constant.POST_METHOD, false, Constant.SIGNATURE_PROGRAM_MODULE, authorizationHeader);
        subjectActivityService.addPastActivityToCurrentPeriod(pastActivityRequests);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

}

