package com.fusm.servicebroker.servicebroker.controller.ms_login;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_authorizer.TokenModel;
import com.fusm.servicebroker.servicebroker.model.ms_login.LogInModel;
import com.fusm.servicebroker.servicebroker.model.ms_login.LogOutModel;
import com.fusm.servicebroker.servicebroker.model.ms_security.BlackListModel;
import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.service.ms_login.ILogInService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Clase que expone los servicios necesarios para realizar el inicio de sesión
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.PUBLIC_ROUTE + AppRoutes.LOGIN_ROUTE)
public class LogInController {

    @Autowired
    private ILogInService logInService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Inicio de sesión
     * @param userRequest Modelo que contiene la ifnormación necesaria para realizar el inicio de sesión dentro de la aplicación
     * @return Información del usuario
     */
    @PostMapping
    @ApiOperation(
            value = "Log in of the application",
            httpMethod = "POST"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success operation"),
            @ApiResponse(code = 400, message = "Parameter not allowed to process"),
            @ApiResponse(code = 403, message = "Not authorized to continue with flow")
    })
    public ResponseEntity<Response<TokenModel>> logIn(
            @Valid @RequestBody LogInModel logInModel
            ) {
        middlewareService.getSecurityApproval(new BlackListModel(logInModel.toString(), false));
        return new ResponseEntity<>(new Response<>(HttpStatus.OK, logInService.logIn(logInModel)), HttpStatus.OK);
    }

    @PostMapping("/logout")
    @ApiOperation(
            value = "Log out of the application",
            httpMethod = "POST"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success operation"),
            @ApiResponse(code = 400, message = "Parameter not allowed to process"),
            @ApiResponse(code = 403, message = "Not authorized to continue with flow")
    })
    public ResponseEntity<Response<String>> logOut(
            @Valid @RequestBody LogOutModel logOutModel
    ) {
        logInService.logOut(logOutModel);
        return ResponseEntity.ok(new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase()));
    }

}
