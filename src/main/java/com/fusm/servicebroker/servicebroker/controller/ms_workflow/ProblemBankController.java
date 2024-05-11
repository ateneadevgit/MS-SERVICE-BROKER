package com.fusm.servicebroker.servicebroker.controller.ms_workflow;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.EvaluateProposalModel;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.ProblemBankRequest;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.SearchModelProblem;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.IProblemBankService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Clase que expone los servicios relacionados con el banco de problemas
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value =  AppRoutes.PUBLIC_ROUTE + AppRoutes.WORKFLOW_ROUTE + "/problem")
public class ProblemBankController {

    @Autowired
    private IProblemBankService problemBankService;

    @Autowired
    private IMiddlewareService middlewareService;

    /**
     * Crea un nuevo problema en el banco de problemas
     * @param problemBankRequest Modelo que contiene la información para crear un nuevo problema
     * @return OK
     */
    @PostMapping
    private ResponseEntity<Response<String>> createProblemBank(
            @RequestBody ProblemBankRequest problemBankRequest,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(problemBankRequest.toString(), Constant.POST_METHOD, false, Constant.NIFS_MODULE, authorizationHeader);
        problemBankService.createProblemBank(problemBankRequest);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Obtiene el banco de problemas del NIF
     * @param searchModel Modelo que contiene los parámetros de búsqueda para filtrar
     * @return lista de problemas
     */
    @PostMapping("/nif")
    private ResponseEntity<Response<Object>> getNifProblemBank(
            @RequestBody SearchModelProblem searchModel,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.NIFS_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, problemBankService.getNifProblemBank(searchModel))
        );
    }

    /**
     * Ontiene el banco de problemas
     * @param searchModel Modelo que contiene los parámetros de búsqueda para filtrar
     * @return lista de problemas
     */
    @PostMapping("/get")
    private ResponseEntity<Response<Object>> getProblemBank(
            @RequestBody SearchModelProblem searchModel,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.PROBLEM_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, problemBankService.getProblemBank(searchModel))
        );
    }

    /**
     * Actualiza un problema
     * @param problemBankRequest Modelo que contiene la información a actualizar
     * @param problemBankId ID del problema
     * @return OK
     */
    @PutMapping("/{id}")
    private ResponseEntity<Response<String>> updateProblemBank(
            @RequestBody ProblemBankRequest problemBankRequest,
            @PathVariable("id") Integer problemBankId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(problemBankRequest.toString(), Constant.PUT_METHOD, false, Constant.NIFS_MODULE, authorizationHeader);
        problemBankService.updateProblemBank(problemBankRequest, problemBankId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Evalúa el problema
     * @param evaluateProposalModel Modelo que contiene la información para evaluar el problema
     * @param problemBankId ID del problema
     * @return OK
     */
    @PostMapping("/{id}/evaluate")
    private ResponseEntity<Response<String>> evaluateProblemBank(
            @RequestBody EvaluateProposalModel evaluateProposalModel,
            @PathVariable("id") Integer problemBankId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(evaluateProposalModel.toString(), Constant.POST_METHOD, false, Constant.NIFS_MODULE, authorizationHeader);
        problemBankService.evaluateProblemBank(evaluateProposalModel, problemBankId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Habilita o deshabilita un problema
     * @param userData Contiene la información del usuario
     * @param problemBankId ID del problema
     * @param enabled TRUE o FALSE en caso que se quiera habilitar o deshabilitar el problema
     * @return OK
     */
    @DeleteMapping("/{id}/dis-enable/{enabled}")
    private ResponseEntity<Response<String>> disableProblemBank(
            @PathVariable("id") Integer problemBankId,
            @PathVariable("enabled") Boolean enabled,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.DELETE_METHOD, false, Constant.NIFS_MODULE, authorizationHeader);
        problemBankService.enableDisableProblemBank(problemBankId, enabled);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

}
