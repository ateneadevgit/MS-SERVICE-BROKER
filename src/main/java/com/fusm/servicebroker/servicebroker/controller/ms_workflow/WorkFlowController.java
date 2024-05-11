package com.fusm.servicebroker.servicebroker.controller.ms_workflow;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.EvaluateProposalModel;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.WorkflowRequest;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.IWorkFlowService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Clase que expone servicios para la evaluación de una propuesta académica
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value =  AppRoutes.PUBLIC_ROUTE + AppRoutes.WORKFLOW_ROUTE)
public class WorkFlowController {

    @Autowired
    private IWorkFlowService workFlowService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Evalúa una propuesta académica
     * @param evaluateProposalModel Modelo que contiene la información para evaluar una propuesta académica
     * @param proposalId ID propuesta
     * @return OK
     */
    @PostMapping("/proposal/{id}")
    @ApiOperation(
            value = "Evaluate proposal"
    )
    public ResponseEntity<Response<String>> evaluateProposal(
            @Valid @RequestBody EvaluateProposalModel evaluateProposalModel,
            @PathVariable("id") Integer proposalId,
            @RequestHeader("Authorization") String authorizationHeader
            ) {
        middlewareService.allMiddlewares(evaluateProposalModel.toString(), Constant.POST_METHOD, false, Constant.WORKFLOW_MODULE, authorizationHeader);
        workFlowService.evaluateProposal(evaluateProposalModel, proposalId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Declina o desahbilita un programa
     * @param evaluateProposalModel Modelo que contiene la información para declinar o deshabilitar un programa
     * @param programId ID del programa
     * @return OK
     */
    @PutMapping("/decline-disable/program/{id}")
    @ApiOperation(
            value = "Disable or decline program"
    )
    public ResponseEntity<Response<String>> declineOrDisableProgram(
            @RequestBody EvaluateProposalModel evaluateProposalModel,
            @PathVariable("id") Integer programId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(evaluateProposalModel.toString(), Constant.PUT_METHOD, false, Constant.WORKFLOW_MODULE, authorizationHeader);
        workFlowService.declineOrDisableProgram(evaluateProposalModel, programId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Obtiene los flujos de trabajo
     * @return lista de flujos de trabajo
     */
    @GetMapping
    private ResponseEntity<Response<Object>> getWorkflows(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.FLOW_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, workFlowService.getWorkflows())
        );
    }

    /**
     * Actualiza un flujo de trabajo
     * @param workflowId ID del flujo de trabajo
     * @param workflowRequest Modelo que contiene la información necesaria para ctualizar un flujo de trabajo
     * @return OK
     */
    @PutMapping("/{id}")
    private ResponseEntity<Response<String>> updateWorkflow(
            @PathVariable("id") Integer workflowId,
            @RequestBody WorkflowRequest workflowRequest,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(workflowRequest.toString(), Constant.PUT_METHOD, false, Constant.FLOW_MODULE, authorizationHeader);
        workFlowService.updateWorkflow(workflowRequest, workflowId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

}
