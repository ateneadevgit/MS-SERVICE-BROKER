package com.fusm.servicebroker.servicebroker.controller.ms_safety_mesh;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_safety_mesh.ColumnRequest;
import com.fusm.servicebroker.servicebroker.service.ms_safety_mesh.IColumnService;
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
 * Clase que expone los servicios relacionados con las columnas de las tablas a las que puede acceder un usuario
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.PUBLIC_ROUTE + AppRoutes.COLUMN_ROUTE)
public class ColumnController {

    @Autowired
    private IColumnService columnService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Obtiene las columnas
     * @return lista de columnas
     */
    @GetMapping
    @ApiOperation(
            value = "Get columns"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Header Authorization which value is the user token", required = true, paramType = "header", dataType = "String", example = "Bearer access_token"),
    })
    public ResponseEntity<Response<Object>> getColumns(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.COLUMN_MODULE, authorizationHeader);
        return ResponseEntity.ok(new Response<>(HttpStatus.OK, columnService.getColumns()));
    }

    /**
     * Crea una nueva columna
     * @param columnRequest Modelo que permite crear una nueva columna
     * @return OK
     * @throws JsonProcessingException
     */
    @PostMapping
    @ApiOperation(
            value = "Create column"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Header Authorization which value is the user token", required = true, paramType = "header", dataType = "String", example = "Bearer access_token"),
    })
    public ResponseEntity<Response<String>> createColumn(
            @Valid @RequestBody ColumnRequest columnRequest,
            @RequestHeader("Authorization") String authorizationHeader
            ) {
        middlewareService.allMiddlewares(columnRequest.toString(), Constant.POST_METHOD, false, Constant.COLUMN_MODULE, authorizationHeader);
        columnService.createColumn(columnRequest);
        return ResponseEntity.ok(new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase()));
    }

    /**
     * Actualizar columna
     * @param columnRequest Modelo que contiene la información para actualizar una columna
     * @param columnId ID de la columna
     * @return OK
     * @throws JsonProcessingException
     */
    @PutMapping("/{id}")
    @ApiOperation(
            value = "Update column"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Header Authorization which value is the user token", required = true, paramType = "header", dataType = "String", example = "Bearer access_token"),
    })
    public ResponseEntity<Response<String>> updateColumn(
            @Valid @RequestBody ColumnRequest columnRequest,
            @PathVariable("id") Integer columnId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(columnRequest.toString(), Constant.PUT_METHOD, false, Constant.COLUMN_MODULE, authorizationHeader);
        columnService.updateColumn(columnRequest, columnId);
        return ResponseEntity.ok(new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase()));
    }

}
