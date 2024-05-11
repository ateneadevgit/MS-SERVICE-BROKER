package com.fusm.servicebroker.servicebroker.controller.ms_workflow;

import com.fusm.servicebroker.servicebroker.exception.GlobalCustomException;
import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.SendEvaluationRequest;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.StepAttachRequest;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.IWorkflowBaseStepService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

/**
 * Clase que expone los servicios relacionados con los pasos y flujo de trabajo para la creación de un programa académico
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.PUBLIC_ROUTE + AppRoutes.WORKFLOW_ROUTE + "/step")
public class WorkflowBaseStepController {

    @Autowired
    private IWorkflowBaseStepService workflowBaseStepService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Obtiene los pasos de un flujo de trabajo
     * @param objectId ID del programa
     * @return flujo de trabajo
     */
    @GetMapping("/by-object/{id}")
    public ResponseEntity<Response<Object>> getSteps(
            @PathVariable("id") Integer objectId,
            @RequestParam Map<String, Object> params,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        String workflowType = params.get("type") != null ? params.get("type").toString() : null;
        if (workflowType == null) throw new GlobalCustomException(HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST);
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.WORKFLOW_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, workflowBaseStepService.getStepsOfWorkflowByRole(objectId, workflowType))
        );
    }

    /**
     * Carga un anexo al flujo
     * @param stepAttachRequest Modelo que contiene la información para cargar un anexo
     * @return OK
     * @throws IOException
     */
    @PostMapping("/attach")
    public ResponseEntity<Response<String>> loadAttachment(
            @Valid @RequestBody StepAttachRequest stepAttachRequest,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(stepAttachRequest.toString(), Constant.POST_METHOD, false, Constant.WORKFLOW_MODULE, authorizationHeader);
        workflowBaseStepService.loadAttachment(stepAttachRequest);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Elimina un anexo al flujo de trabajo
     * @param attachId ID del anexo
     * @return OK
     */
    @DeleteMapping("/attach/{id}")
    public ResponseEntity<Response<String>> disableAttachment(
            @PathVariable("id") Integer attachId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.DELETE_METHOD, false, Constant.WORKFLOW_MODULE, authorizationHeader);
        workflowBaseStepService.disableAttachment(attachId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Envia paso a evaluación
     * @param sendEvaluationRequest Modelo que contiene la información para enviar un paso a evaluaicón
     * @return OK
     * @throws IOException
     */
    @PostMapping("/send/evaluation")
    public ResponseEntity<Response<String>> sendStepToEvaluation(
            @Valid @RequestBody SendEvaluationRequest sendEvaluationRequest,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(sendEvaluationRequest.toString(), Constant.POST_METHOD, false, Constant.WORKFLOW_MODULE, authorizationHeader);
        workflowBaseStepService.sendStepToEvaluation(sendEvaluationRequest);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

}
