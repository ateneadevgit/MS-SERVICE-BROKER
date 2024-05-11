package com.fusm.servicebroker.servicebroker.controller.ms_sinu;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_sinu.SearchDirectory;
import com.fusm.servicebroker.servicebroker.model.ms_sinu.SearchRoles;
import com.fusm.servicebroker.servicebroker.service.ms_sinu.ISinuService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Clase que expone los servicios consumidos de SINU
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.PUBLIC_ROUTE + AppRoutes.SINU_ROUTE)
public class SinuController {

    @Autowired
    private ISinuService sinuService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Obtiene los usuarios según un rol
     * @param roleId ID del rol
     * @return lista de usuarios
     */
    @GetMapping("/user/by-role/{id}")
    @ApiOperation(
            value = "Get users by role from SINU"
    )
    public ResponseEntity<Response<Object>> getUserByRole(
            @PathVariable("id") Integer roleId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.SINU_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, sinuService.getUsersByRole(roleId))
        );
    }

    /**
     * Obtiene los usuarios segun varios roles
     * @param roles Lista de roles
     * @return lista de usuarios
     */
    @PostMapping("/user/by-role")
    private ResponseEntity<Response<Object>> getUserByRoles(
            @RequestBody SearchRoles roles
    ) {
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, sinuService.getUserByRolesIds(roles))
        );
    }

    /**
     * Obtiene una lista de todos los usuarios registrados en la aplicación
     * @param searchDirectory Modelo que permite realizar la búsqueda y aplicar filtros
     * @return lista de usuarios
     */
    @PostMapping("/directory")
    private ResponseEntity<Response<Object>> getDirectory(
            @RequestBody SearchDirectory searchDirectory,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.DIRECTORY_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, sinuService.getDirectory(searchDirectory))
        );
    }

    /**
     * Obtiene la lista de usuarios con rol estudiante por facultad
     * @param facultyId ID de la facultad
     * @return lista de estudiantes
     */
    @GetMapping("/user/by-faculty/{facultyId}")
    private ResponseEntity<Response<Integer>> getUsersByFaculty(
            @PathVariable("facultyId") Integer facultyId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.DASHBOARD_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, sinuService.getStudentsByFaculty(facultyId))
        );
    }

    /**
     * Obtiene la cantidad de mujeres y hombres registrados en la aplicación
     * @return lista de usuarios
     */
    @GetMapping("/user/by-gender")
    private ResponseEntity<Response<Object>> getUserGender(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.DASHBOARD_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, sinuService.getUserGender())
        );
    }


}
