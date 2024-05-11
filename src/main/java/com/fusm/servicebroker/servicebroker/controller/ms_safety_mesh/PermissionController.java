package com.fusm.servicebroker.servicebroker.controller.ms_safety_mesh;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_safety_mesh.PermissionRequest;
import com.fusm.servicebroker.servicebroker.service.ms_safety_mesh.IPermissionService;
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
import java.util.List;

/**
 * Clase que expone los servicios relacionados con los permisos de la aplicación
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value =  AppRoutes.PUBLIC_ROUTE + AppRoutes.PERMISSION_ROUTE)
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Obtiene los permisos que tiene un rol
     * @param roleId ID del rol
     * @return lista de permisos
     */
    @GetMapping("/by-role/{id}")
    @ApiOperation(
            value = "Get permission by role"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Header Authorization which value is the user token", required = true, paramType = "header", dataType = "String", example = "Bearer access_token"),
    })
    private ResponseEntity<Response<Object>> getPermissionByRole(
            @PathVariable("id") Integer roleId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.MODULE_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, permissionService.getPermissionByRole(roleId))
        );
    }

    /**
     * Crea un nuevo permiso
     * @param permissionRequest Modelo que contiene la información necesaria para crear un permiso
     * @return OK
     */
    @PostMapping
    @ApiOperation(
            value = "Create permission"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Header Authorization which value is the user token", required = true, paramType = "header", dataType = "String", example = "Bearer access_token"),
    })
    private ResponseEntity<Response<String>> createPermission(
            @Valid @RequestBody PermissionRequest permissionRequest,
            @RequestHeader("Authorization") String authorizationHeader
            ) {
        middlewareService.allMiddlewares(permissionRequest.toString(), Constant.POST_METHOD, false, Constant.MODULE_MODULE, authorizationHeader);
        permissionService.createPermission(permissionRequest);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Actualiza un permiso
     * @param permissionRequest Modelo que contiene la información necesaria para actualizar un permiso
     * @return OK
     */
    @PutMapping
    @ApiOperation(
            value = "Update permission"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Header Authorization which value is the user token", required = true, paramType = "header", dataType = "String", example = "Bearer access_token"),
    })
    private ResponseEntity<Response<String>> updatePermission(
            @Valid @RequestBody PermissionRequest permissionRequest,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(permissionRequest.toString(), Constant.PUT_METHOD, false, Constant.MODULE_MODULE, authorizationHeader);
        permissionService.updatePermission(permissionRequest);
        return ResponseEntity.ok(new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase()));
    }

    /**
     * Obtiene los módulos a los que tiene acceso un usuario según su rol
     * @param roleId ID del rol
     * @return lista de módulos
     */
    @GetMapping("/module/by-role/{id}")
    @ApiOperation(
            value = "Get module by role"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Header Authorization which value is the user token", required = true, paramType = "header", dataType = "String", example = "Bearer access_token"),
    })
    private ResponseEntity<Response<Object>> getModuleByRole(
            @PathVariable("id") Integer roleId
    ) {
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, permissionService.getModuleByRole(roleId))
        );
    }

    /**
     * Obtiene los módulos flotantes a los que tiene acceso un usuario según su rol
     * @param roleId ID del rol
     * @return lista de módulos flotantes
     */
    @GetMapping("/floating/module/by-role/{id}")
    @ApiOperation(
            value = "Get floating module by role"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Header Authorization which value is the user token", required = true, paramType = "header", dataType = "String", example = "Bearer access_token"),
    })
    public ResponseEntity<Response<Object>> getFloatingModuleByRole(
            @PathVariable("id") Integer roleId
    ) {
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, permissionService.getFloatingModuleByRole(roleId))
        );
    }

    /**
     * Obtiene los permisos sobre un módulo
     * @param moduleId ID del módulo
     * @return lista de permisos
     */
    @GetMapping("/module-id/{id}")
    public ResponseEntity<Response<Object>> getPermissionsByModule(
            @PathVariable("id") Integer moduleId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.MODULE_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, permissionService.getPermissionsByModule(moduleId))
        );
    }

}
