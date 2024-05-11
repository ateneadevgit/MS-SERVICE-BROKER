package com.fusm.servicebroker.servicebroker.controller.ms_workflow;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.CurriculumSummaryRequest;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.UpdateCurriculumSummary;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.ICurriculumSummaryService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * Clase que expone los servicios relacionados con los resumenes específicos de la creación de un programa académico
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.PUBLIC_ROUTE + AppRoutes.WORKFLOW_ROUTE + "/curriculum/summary")
public class CurriculumSummaryController {

    @Autowired
    private ICurriculumSummaryService curriculumSummaryService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Crea un resumen específico
     * @param curriculumSummaryRequest Modelo que contiene la información para crear un resumen específico
     * @return OK
     */
    @PostMapping
    public ResponseEntity<Response<String>> createCurriculumSummary(
            @Valid @RequestBody CurriculumSummaryRequest curriculumSummaryRequest,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(curriculumSummaryRequest.toString(), Constant.POST_METHOD, true, Constant.WORKFLOW_MODULE, authorizationHeader);
        curriculumSummaryService.createCurruculumSummary(curriculumSummaryRequest);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Obtener el resumen específico
     * @param curriculumType ID del tipo
     * @return resumen específico
     */
    @GetMapping("/{type}")
    public ResponseEntity<Response<Object>> getCurriculumSummary(
            @PathVariable("type") Integer curriculumType,
            @RequestParam Map<String, Object> params,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.WORKFLOW_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, curriculumSummaryService.getCurriculumSummary(params, curriculumType))
        );
    }

    /**
     * Actualizar el resumen específico
     * @param updateCurriculumSummary Modelo que contiene información para actualizar el resumen específico
     * @param curriculumId ID del resumen específico
     * @return OK
     */
    @PutMapping("/{id}")
    public ResponseEntity<Response<String>> updateCurriculumSummary(
            @RequestBody UpdateCurriculumSummary updateCurriculumSummary,
            @PathVariable("id") Integer curriculumId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(updateCurriculumSummary.toString(), Constant.PUT_METHOD, true, Constant.WORKFLOW_MODULE, authorizationHeader);
        curriculumSummaryService.updateCurruculumSummary(updateCurriculumSummary, curriculumId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Obtiene el resumen específico según el programa y el tipo
     * @param typeId ID del tipo
     * @param objectId ID del programa
     * @return resumen específico
     */
    @GetMapping("/type/{type}/object-id/{id}")
    public ResponseEntity<Response<Object>> getCurriculumSummaryByProgram(
            @PathVariable("type") Integer typeId,
            @PathVariable("id") Integer objectId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, true, Constant.WORKFLOW_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, curriculumSummaryService.getCurriculumSummaryByProgram(objectId, typeId))
        );
    }

}
