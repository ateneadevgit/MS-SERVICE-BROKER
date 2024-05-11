package com.fusm.servicebroker.servicebroker.controller.ms_program;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_program.TraceabilityRequest;
import com.fusm.servicebroker.servicebroker.service.ms_program.ITraceabilityService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Clase que expone los servicios relacionados con el último formulario de los pasos de la creación de un programa académico
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.PUBLIC_ROUTE + AppRoutes.TRACEABILITY_ROUTE)
public class TraceabilityController {

    @Autowired
    private ITraceabilityService traceabilityService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Obtener la trazabilidad de un programa
     * @param programId ID del programa
     * @return trazabilidad del programa
     */
    @GetMapping("/program-id/{id}")
    public ResponseEntity<Response<Object>> getProgramTraceability(
            @PathVariable("id") Integer programId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.PROGRAM_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, traceabilityService.getProgramTraceability(programId))
        );
    }

    /**
     * Crear la trazabilidad de un programa académico
     * @param programId ID del programa
     * @param traceabilityRequest Modelo que contiene la información necesaria para crear la trazabilidad de un programa
     * @return OK
     */
    @PostMapping("/program-id/{id}")
    public ResponseEntity<Response<String>> createProgramTraceability(
            @PathVariable("id") Integer programId,
            @Valid @RequestBody TraceabilityRequest traceabilityRequest,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(traceabilityRequest.toString(), Constant.POST_METHOD, false, Constant.PROGRAM_MODULE, authorizationHeader);
        traceabilityService.createTraceability(traceabilityRequest, programId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Actualiza la trazabilidad de un programa académico
     * @param programId ID del programa
     * @param traceabilityRequest Modelo que contiene la información necesaria para actualizar la trazabilidad de un programa
     * @return OK
     */
    @PutMapping("/program-id/{id}")
    public ResponseEntity<Response<String>> updateProgramTraceability(
            @PathVariable("id") Integer programId,
            @Valid @RequestBody TraceabilityRequest traceabilityRequest,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(traceabilityRequest.toString(), Constant.PUT_METHOD, false, Constant.PROGRAM_MODULE, authorizationHeader);
        traceabilityService.updateTraceability(traceabilityRequest, programId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

}
