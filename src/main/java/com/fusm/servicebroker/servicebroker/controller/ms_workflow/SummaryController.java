package com.fusm.servicebroker.servicebroker.controller.ms_workflow;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.SummaryRequest;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.ISummaryService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Clase que expone los servicios de los resumenes de los pasos de la creación de un programa académico
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.PUBLIC_ROUTE + AppRoutes.WORKFLOW_ROUTE + "/summary")
public class SummaryController {

    @Autowired
    private ISummaryService summaryService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Crea un resumen
     * @param summaryRequest Modelo que contiene la información para crear un resumen
     * @return OK
     */
    @PostMapping
    private ResponseEntity<Response<String>> createSummary(
            @RequestBody SummaryRequest summaryRequest,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(summaryRequest.toString(), Constant.POST_METHOD, true, Constant.WORKFLOW_MODULE, authorizationHeader);
        summaryService.createSummary(summaryRequest);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Obtiene el resumen de un paso
     * @return resumen
     */
    @GetMapping
    private ResponseEntity<Response<Object>> getSummary(
            @RequestParam Map<String, Object> params,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, true, Constant.WORKFLOW_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, summaryService.getSummary(params))
        );
    }

    /**
     * Obtiene los componentes curriculares según un programa
     * @param objectId ID del programa
     * @param type ID del tipo
     * @return resumen
     */
    @GetMapping("/curricular/object-id/{id}/type/{type}")
    private ResponseEntity<Response<Object>> getCurricularComponentByProgram(
            @PathVariable("id") Integer objectId,
            @PathVariable("type") Integer type,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, true, Constant.WORKFLOW_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, summaryService.getSummaryByProgramAndType(objectId, type))
        );
    }

    /**
     * Actualiza un resumen
     * @param summaryRequest Modelo que contiene la información para actualizar el resumen
     * @return OK
     */
    @PutMapping
    private ResponseEntity<Response<String>> updateSummary(
            @RequestBody SummaryRequest summaryRequest,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(summaryRequest.toString(), Constant.PUT_METHOD, true, Constant.WORKFLOW_MODULE, authorizationHeader);
        summaryService.updateSummary(summaryRequest);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Envía el resumen a evaluar
     * @param summaryId ID del resumen
     * @return OK
     */
    @GetMapping("/{id}")
    private ResponseEntity<Response<String>> sendSummaryToEvaluation(
            @PathVariable("id") Integer summaryId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, true, Constant.WORKFLOW_MODULE, authorizationHeader);
        summaryService.sendSummaryToEvaluation(summaryId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Obtiene si el resumen ya fue evaluado por el rol especificado
     * @param summaryId ID del resumen
     * @return TRUE o FALSE
     */
    @GetMapping("/{id}/is-evaluated")
    private ResponseEntity<Response<Boolean>> hasAlreadyEvaluated(
            @PathVariable("id") Integer summaryId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, true, Constant.WORKFLOW_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, summaryService.hasAlreadyEvaluated(summaryId))
        );
    }

    /**
     * Obtiene si el resumen ya fue enviado a revisión
     * @param summaryId ID del resumen
     * @return TRUE o FALSE
     */
    @GetMapping("/{id}/sended")
    private ResponseEntity<Response<Boolean>> hasAlreadySendToEvaluation(
            @PathVariable("id") Integer summaryId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, true, Constant.WORKFLOW_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, summaryService.hasAlreadySendToEvaluation(summaryId))
        );
    }

}
