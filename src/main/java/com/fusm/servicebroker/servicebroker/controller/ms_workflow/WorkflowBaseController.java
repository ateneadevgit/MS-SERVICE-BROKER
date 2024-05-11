package com.fusm.servicebroker.servicebroker.controller.ms_workflow;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.UserData;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.WorkflowBaseRequest;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.IWorkflowBaseService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Clase que expone los servicios relacionados con el flujo de trabajo para la creación de un programa académico
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.PUBLIC_ROUTE + AppRoutes.WORKFLOW_ROUTE + "/base")
public class WorkflowBaseController {

    @Autowired
    private IWorkflowBaseService workflowBaseService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Crea un flujo de trabajo
     * @param workflowBaseRequest Modelo que contiene la información necesaria para crear un flujo de trabajo
     * @return OK
     */
    @PostMapping
    public ResponseEntity<Response<String>> createWorkflowBase(
            @Valid @RequestBody WorkflowBaseRequest workflowBaseRequest,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(workflowBaseRequest.toString(), Constant.POST_METHOD, false, Constant.WORKFLOW_MODULE, authorizationHeader);
        workflowBaseService.createWorkflowBase(workflowBaseRequest);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Obtiene si el flujo ha iniciado o no
     * @param programId ID del programa
     * @return TRUE o FALSE
     */
    @GetMapping("/started/{id}")
    public ResponseEntity<Response<Boolean>> hasFlowStarted(
            @PathVariable("id") Integer programId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.WORKFLOW_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, workflowBaseService.hasFlowStarted(programId))
        );
    }

    /**
     * Relaciona un usuario a un flujo de trabajo
     * @param userData Modelo que contiene la información de un usuario
     * @param objectId ID del programa
     * @return OK
     */
    @PostMapping("/relate-user/object-id/{id}")
    public ResponseEntity<Response<String>> relateUserToWorkflow(
            @RequestBody UserData userData,
            @PathVariable("id") Integer objectId
    ) {
        workflowBaseService.relateUserToWorkflow(userData, objectId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Relaciona un usuario a un flujo de trabajo padre
     * @param userData Modelo que contiene la información de un usuario
     * @param objectId ID del programa
     * @return OK
     */
    @PostMapping("/relate-user/father/object-id/{id}")
    public ResponseEntity<String> relateUserToWorkflowFather(
            @RequestBody UserData userData,
            @PathVariable("id") Integer objectId
    ) {
        workflowBaseService.relateUserToWorkflowFather(userData, objectId);
        return ResponseEntity.ok(HttpStatus.OK.getReasonPhrase());
    }

}
