package com.fusm.servicebroker.servicebroker.controller.ms_safety_mesh;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_safety_mesh.ModuleRequest;
import com.fusm.servicebroker.servicebroker.service.ms_safety_mesh.IModuleService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Clase que expone los servicios relacionados con los módulos de la aplicación
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.PUBLIC_ROUTE + AppRoutes.MODULE_ROUTE)
public class ModuleController {

    @Autowired
    private IModuleService moduleService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Obtiene los módulos de la aplicación
     * @return lista de módulos
     */
    @GetMapping
    @ApiOperation(
            value = "Get modules"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Header Authorization which value is the user token", required = true, paramType = "header", dataType = "String", example = "Bearer access_token"),
    })
    public ResponseEntity<Response<Object>> getModules(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.MODULE_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, moduleService.getModules())
        );
    }

    /**
     * Crea un módulo
     * @param moduleRequest Modelo que contiene la información para crear un nuevo módulo
     * @return OK
     */
    @PostMapping
    @ApiOperation(
            value = "Create module"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Header Authorization which value is the user token", required = true, paramType = "header", dataType = "String", example = "Bearer access_token"),
    })
    public ResponseEntity<Response<String>> createModule(
            @Valid @RequestBody ModuleRequest moduleRequest,
            @RequestHeader("Authorization") String authorizationHeader
            ) {
        middlewareService.allMiddlewares(moduleRequest.toString(), Constant.POST_METHOD, false, Constant.MODULE_MODULE, authorizationHeader);
        moduleService.createModule(moduleRequest);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Actualiza un módulo
     * @param moduleRequest Modelo que contiene la información para actualizar un módulo
     * @param moduleId ID del módulo
     * @return OK
     */
    @PutMapping("/{id}")
    @ApiOperation(
            value = "Update module"
    )
    public ResponseEntity<Response<String>> updateModule(
            @RequestBody ModuleRequest moduleRequest,
            @PathVariable("id") Integer moduleId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(moduleRequest.toString(), Constant.PUT_METHOD, false, Constant.MODULE_MODULE, authorizationHeader);
        moduleService.updateModule(moduleRequest, moduleId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

}
