package com.fusm.servicebroker.servicebroker.controller.ms_program;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_program.EvaluateProposalRenovationRequest;
import com.fusm.servicebroker.servicebroker.model.ms_program.ProposalUpgradeRequest;
import com.fusm.servicebroker.servicebroker.service.ms_program.IProposalRenovationService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Clase que expone los servicios necesarios para la edición de un programa académico
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value =  AppRoutes.PUBLIC_ROUTE + AppRoutes.PROGRAM_ROUTE + "/upgrade")
public class ProposalRenovationController {

    @Autowired
    private IProposalRenovationService proposalRenovationService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Crea una propuesta de renovación
     * @param programId ID del programa
     * @param proposalUpgradeRequest Modelo que contiene la información de la propuesta de renovación a crear
     * @return OK
     */
    @PostMapping("/{id}")
    public ResponseEntity<Response<String>> createProposalRenovation(
            @PathVariable("id") Integer programId,
            @RequestBody ProposalUpgradeRequest proposalUpgradeRequest,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.PROGRAM_MODULE, authorizationHeader);
        proposalRenovationService.createProposalRenovation(proposalUpgradeRequest, programId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Evalúa la propuesta de renovación
     * @param programId ID del programa
     * @param evaluateProposalRenovationRequest Modelo que contiene la información necesaria para evaluar la propuesta de renovación
     * @return OK
     */
    @PostMapping("/evaluate/{id}")
    public ResponseEntity<Response<String>> evaluateProposalRenovation(
            @PathVariable("id") Integer programId,
            @RequestBody EvaluateProposalRenovationRequest evaluateProposalRenovationRequest,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.PROGRAM_MODULE, authorizationHeader);
        proposalRenovationService.evaluateProposalRenovation(evaluateProposalRenovationRequest, programId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Obtiene los modulos seleccionados para la edición
     * @param programId ID del programa
     * @return lista de módulos
     */
    @GetMapping("/evaluate/{id}")
    public ResponseEntity<Response<Object>> getSelectedModules(
            @PathVariable("id") Integer programId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.PROGRAM_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, proposalRenovationService.getSelectedModules(programId))
        );
    }

    /**
     * Obtiene una lista de los módulos que se encuentran en edición
     * @param programId ID del programa
     * @return lista de módulos
     */
    @GetMapping("/edition/{id}")
    public ResponseEntity<Response<Object>> getModulesOnEdition(
            @PathVariable("id") Integer programId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.PROGRAM_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, proposalRenovationService.getOnEditionModules(programId))
        );
    }

    /**
     * Obtiene la propuesta de edición de un programa académico
     * @param programId ID del programa
     * @return renovación
     */
    @GetMapping("/program-id/{id}")
    private ResponseEntity<Response<Object>> getProposalRenovation(
            @PathVariable("id") Integer programId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.PROGRAM_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, proposalRenovationService.getRequestProposal(programId))
        );
    }

}

