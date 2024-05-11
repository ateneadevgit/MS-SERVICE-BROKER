package com.fusm.servicebroker.servicebroker.controller.ms_workflow;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.EvaluateGuide;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.SearchTeacher;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.SubjectGuideRequest;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.ISubjectGuideService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Clase que expone los servicios relacionados con las guias de asignatura
 * ITSense Inc - Andrea Gómez
 */


@RestController
@RequestMapping(value = AppRoutes.PUBLIC_ROUTE + AppRoutes.WORKFLOW_ROUTE + "/guide")
public class SubjectGuideController {

    @Autowired
    private ISubjectGuideService subjectGuideService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Crea una guía de asignatura
     * @param subjectGuideRequest Modelo que contiene la información para crear una guía
     * @param curriculumId ID de la asignatura
     * @return OK
     */
    @PostMapping("/curriculum-id/{id}")
    private ResponseEntity<Response<String>> createSubjectGuide(
            @RequestBody SubjectGuideRequest subjectGuideRequest,
            @PathVariable("id") Integer curriculumId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(subjectGuideRequest.toString(), Constant.POST_METHOD, false, Constant.SIGNATURE_PROGRAM_MODULE, authorizationHeader);
        subjectGuideService.createSubjectGuide(subjectGuideRequest, curriculumId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Actualiza una guía de asignatura
     * @param subjectGuideRequest Modelo que contiene la información a actualizar de la guía
     * @param curriculumId ID de la asignatura
     * @return OK
     */
    @PutMapping("/curriculum-id/{id}")
    private ResponseEntity<Response<String>> updateSubjectGuide(
            @RequestBody SubjectGuideRequest subjectGuideRequest,
            @PathVariable("id") Integer curriculumId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(subjectGuideRequest.toString(), Constant.PUT_METHOD, false, Constant.SIGNATURE_PROGRAM_MODULE, authorizationHeader);
        subjectGuideService.updateSubjectGuide(subjectGuideRequest, curriculumId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Obtiene la guía de asignatura según el ID de un curriculum
     * @param curriculumId ID de la asignatura
     * @param searchTeacher Modelo que contiene la información del docente
     * @return guía de asignatura
     */
    @PostMapping("/curriculum-id/{id}/data")
    private ResponseEntity<Response<Object>> getSubjectGuide(
            @RequestBody SearchTeacher searchTeacher,
            @PathVariable("id") Integer curriculumId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares("test", Constant.POST_METHOD, false, Constant.SIGNATURE_PROGRAM_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, subjectGuideService.getSubjectGuide(searchTeacher, curriculumId))
        );
    }

    /**
     * Obtiene el PDF de la guía de asignatura
     * @param searchTeacher Modelo que contiene la información del docente
     * @param curriculumId ID de la asignatura
     * @return URL del PDF
     */
    @PostMapping("/pdf/curriculum-id/{id}")
    private ResponseEntity<Response<String>> getSubjectGuidePdf(
            @RequestBody SearchTeacher searchTeacher,
            @PathVariable("id") Integer curriculumId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares("test", Constant.POST_METHOD, false, Constant.SIGNATURE_PROGRAM_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, subjectGuideService.getSubjectGuidePdf(searchTeacher, curriculumId))
        );
    }

    /**
     * Evalúa una guía de asignatura
     * @param subjectGuideId ID de la guía de asignatura
     * @param evaluateGuide Modelo que permite la evaluación de la guía
     * @return OK
     */
    @PostMapping("/{id}/evaluate")
    private ResponseEntity<Response<String>> evaluateSubjectGuide(
            @PathVariable("id") Integer subjectGuideId,
            @RequestBody EvaluateGuide evaluateGuide,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(evaluateGuide.toString(), Constant.POST_METHOD, false, Constant.SIGNATURE_PROGRAM_MODULE, authorizationHeader);
        subjectGuideService.evaluateSubejctGuide(evaluateGuide, subjectGuideId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Obtiene la información precargada de las guías de asignatura
     * @param curriculumId ID de la asignatura
     * @return data a precargar
     */
    @GetMapping("/pre-load/curriculum-id/{id}")
    private ResponseEntity<Response<Object>> getPreloadData(
            @PathVariable("id") Integer curriculumId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares("test", Constant.POST_METHOD, false, Constant.SIGNATURE_PROGRAM_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, subjectGuideService.getPreloadData(curriculumId))
        );
    }

}

