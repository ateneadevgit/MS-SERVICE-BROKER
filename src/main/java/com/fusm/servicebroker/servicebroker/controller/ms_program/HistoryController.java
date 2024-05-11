package com.fusm.servicebroker.servicebroker.controller.ms_program;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.service.ms_program.IHistoryService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Esta clase esta referenciada hacia el módulo del monitoreo curricular
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.PUBLIC_ROUTE + AppRoutes.PROGRAM_ROUTE + "/history")
public class HistoryController {

    @Autowired
    private IHistoryService historyService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Obtiene el histórico especifico de una entidad
     * @param programId ID del programa
     * @param moduleId ID del módulo
     * @return lista del histórico
     */
    @GetMapping("/program-id/{programId}/module-id/{moduleId}")
    public ResponseEntity<Response<Object>> getHistorySpecific(
            @PathVariable("programId") Integer programId,
            @PathVariable("moduleId") Integer moduleId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.PROGRAM_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, historyService.getHistorySpecific(programId, moduleId))
        );
    }

    /**
     * Obtener el historial por el tipo
     * @param programId ID del programa
     * @param moduleId ID del modulo
     * @param typeId ID del tipo
     * @return lista de historial
     */
    @GetMapping("/program-id/{programId}/module-id/{moduleId}/type/{typeId}")
    public ResponseEntity<Response<Object>> getHistoryByType(
            @PathVariable("programId") Integer programId,
            @PathVariable("moduleId") Integer moduleId,
            @PathVariable("typeId") Integer typeId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.PROGRAM_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, historyService.gitHistoricByType(programId, moduleId, typeId))
        );
    }

}

