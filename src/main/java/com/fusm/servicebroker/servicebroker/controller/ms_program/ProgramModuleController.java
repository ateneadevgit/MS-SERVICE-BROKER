package com.fusm.servicebroker.servicebroker.controller.ms_program;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.service.ms_program.IProgramModuleService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Clase que expone los servicios relacionados con los módulos de un programa académico
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.PUBLIC_ROUTE + AppRoutes.PROGRAM_ROUTE + "/module")
public class ProgramModuleController {

    @Autowired
    private IProgramModuleService programModuleService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Obtiene los módulos de un programa académico
     * @param isEnlarge TRUE o FALSE en caso que sea un programa de renovación o no
     * @return lista de módulos
     */
    @GetMapping
    public ResponseEntity<Response<Object>> getProgramModules(
            @RequestParam("isEnlarge") boolean isEnlarge,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.PROGRAM_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, programModuleService.getProgramModules(isEnlarge))
        );
    }

    /**
     * Lista de los módulos no editables de un programa académico
     * @param isEnlarge TRUE o FALSE en caso que sea un programa de renovación o no
     * @return lista de módulos
     */
    @GetMapping("/no-editable")
    public ResponseEntity<Response<Object>> getProgramModulesNoEditable(
            @RequestParam("isEnlarge") boolean isEnlarge,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.PROGRAM_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, programModuleService.getAllProgramModules(isEnlarge))
        );
    }

}
