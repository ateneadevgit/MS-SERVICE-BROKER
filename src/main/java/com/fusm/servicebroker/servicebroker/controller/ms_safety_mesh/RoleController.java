package com.fusm.servicebroker.servicebroker.controller.ms_safety_mesh;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_safety_mesh.RoleRequest;
import com.fusm.servicebroker.servicebroker.service.ms_safety_mesh.IRoleService;
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
 * Clase que expone los servicios relacionados con los roles que interactúan con la aplicación
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.PUBLIC_ROUTE + AppRoutes.ROLE_ROUTE)
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Obtiene una lista de los roles
     * @return lista de roles
     */
    @GetMapping
    @ApiOperation(
            value = "Get roles"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Header Authorization which value is the user token", required = true, paramType = "header", dataType = "String", example = "Bearer access_token"),
    })
    public ResponseEntity<Response<Object>> getRoles(
    ) {
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, roleService.getRoles())
        );
    }

    /**
     * Crea un nuevo rol
     * @param roleRequest Modelo que contiene la información para crear un nuevo rol
     * @return OK
     */
    @PostMapping
    @ApiOperation(
            value = "Create role"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Header Authorization which value is the user token", required = true, paramType = "header", dataType = "String", example = "Bearer access_token"),
    })
    public ResponseEntity<Response<String>> createRole(
            @Valid @RequestBody RoleRequest roleRequest,
            @RequestHeader("Authorization") String authorizationHeader
            ) {
        middlewareService.allMiddlewares(roleRequest.toString(), Constant.POST_METHOD, false, Constant.ROLE_MODULE, authorizationHeader);
        roleService.createRole(roleRequest);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Actualiza el rol
     * @param roleRequest Modelo que contiene la información necesaria para actualizar un rol
     * @param roleId ID del rol
     * @return OK
     */
    @PutMapping("/{id}")
    @ApiOperation(
            value = "Update role"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Header Authorization which value is the user token", required = true, paramType = "header", dataType = "String", example = "Bearer access_token"),
    })
    public ResponseEntity<Response<String>> updateRole(
            @Valid @RequestBody RoleRequest roleRequest,
            @PathVariable("id") Integer roleId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(roleRequest.toString(), Constant.PUT_METHOD, false, Constant.ROLE_MODULE, authorizationHeader);
        roleService.updateRole(roleRequest, roleId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Habilita o Desahbilita un rol
     * @param roleId ID del rol
     * @param enabled TRUE o FALSE en caso que se quiera habilitar o deshabilitar
     * @return OK
     */
    @DeleteMapping("/{id}/enabled/{enabled}")
    public ResponseEntity<Response<String>> enableDisableRole(
            @PathVariable("id") Integer roleId,
            @PathVariable("enabled") Boolean enabled,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.DELETE_METHOD, false, Constant.ROLE_MODULE, authorizationHeader);
        roleService.enableDisableRole(roleId, enabled);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Obtiene los roles que no tengan permisos dentro de un módulo
     * @param moduleId ID del módulo
     * @return lista de roles
     */
    @GetMapping("/not-included/module-id/{id}")
    public ResponseEntity<Response<Object>> getRolesWithNotPermissionInModule(
            @PathVariable("id") Integer moduleId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.ROLE_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, roleService.getRolesWithNotPermissionInModule(moduleId))
        );
    }

}
