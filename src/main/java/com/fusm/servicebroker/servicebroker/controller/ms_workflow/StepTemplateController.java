package com.fusm.servicebroker.servicebroker.controller.ms_workflow;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.TemplateRequest;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.IStepTemplateService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Clase que expone los servicios relacionados con los anexos mínimos requeridos de los pasos
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.PUBLIC_ROUTE + AppRoutes.WORKFLOW_ROUTE + "/step-template")
public class StepTemplateController {

    @Autowired
    private IStepTemplateService stepTemplateService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Crea un anexo mínimo requerido del paso
     * @param templateRequest Modelo que contiene la información para crear un anexo mínimo
     * @return OK
     */
    @PostMapping
    private ResponseEntity<Response<String>> createTemplate(
            @RequestBody TemplateRequest templateRequest,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(templateRequest.toString(), Constant.POST_METHOD, false, Constant.FLOW_MODULE, authorizationHeader);
        stepTemplateService.createTemplate(templateRequest);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Actualiza un anexo mínimo requerido
     * @param templateRequest Modelo que contiene la información a actualizar de un anexo mínimo
     * @param templateId ID del anexo
     * @return OK
     */
    @PutMapping("/{id}")
    private ResponseEntity<Response<String>> updateTemplate(
            @RequestBody TemplateRequest templateRequest,
            @PathVariable("id") Integer templateId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(templateRequest.toString(), Constant.PUT_METHOD, false, Constant.FLOW_MODULE, authorizationHeader);
        stepTemplateService.updateTemplate(templateRequest, templateId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

}
