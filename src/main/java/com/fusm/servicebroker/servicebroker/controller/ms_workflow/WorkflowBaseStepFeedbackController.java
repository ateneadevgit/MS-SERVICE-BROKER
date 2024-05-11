package com.fusm.servicebroker.servicebroker.controller.ms_workflow;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.EvaluateStepRequest;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.EvaluateTraceability;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.IWorkflowBaseStepFeedbackService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

/**
 * Clase que expone los servicios relacionados con la evaluación de los pasos
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.PUBLIC_ROUTE + AppRoutes.WORKFLOW_ROUTE + "/step/feedback")
public class WorkflowBaseStepFeedbackController {

    @Autowired
    private IWorkflowBaseStepFeedbackService workflowBaseStepFeedbackService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Evalúa un paso
     * @param evaluateStepRequest Modelo que contiene la información para evaluar un paso
     * @return OK
     * @throws IOException
     */
    @PostMapping
    public ResponseEntity<Response<String>> evaluateStep(
            @Valid @RequestBody EvaluateStepRequest evaluateStepRequest,
            @RequestHeader("Authorization") String authorizationHeader
            ) {
        middlewareService.allMiddlewares(evaluateStepRequest.toString(), Constant.POST_METHOD, false, Constant.WORKFLOW_MODULE, authorizationHeader);
        workflowBaseStepFeedbackService.evaluateStep(evaluateStepRequest);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Evalúa el formulario final del los pasos
     * @param evaluateTraceability Modelo que contiene la información para evaluar la trazabilidad de un programa
     * @param programId ID del programa
     * @return OK
     */
    @PostMapping("/traceability/program-id/{id}")
    public ResponseEntity<Response<String>> evaluateTraceability(
            @Valid @RequestBody EvaluateTraceability evaluateTraceability,
            @PathVariable("id") Integer programId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(evaluateTraceability.toString(), Constant.POST_METHOD, false, Constant.WORKFLOW_MODULE, authorizationHeader);
        workflowBaseStepFeedbackService.evaluateTraceability(evaluateTraceability, programId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

}
