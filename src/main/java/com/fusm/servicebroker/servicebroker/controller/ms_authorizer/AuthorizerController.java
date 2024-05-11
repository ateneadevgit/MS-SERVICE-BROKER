package com.fusm.servicebroker.servicebroker.controller.ms_authorizer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_authorizer.RequestTokenModel;
import com.fusm.servicebroker.servicebroker.service.ms_authorizer.IAuthorizerService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Esta clase pertime la exposición de los servicios relacionados con el token y autenticación del usuario
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.PUBLIC_ROUTE + AppRoutes.AUTHORIZER_ROUTE)
public class AuthorizerController {

    @Autowired
    private IAuthorizerService authorizerService;


    /**
     * Refresca el token
     * @param requestTokenModel Modelo que contiene la información del token a refrescar
     * @return Token
     * @throws JsonProcessingException
     */
    @PostMapping("/refresh")
    public ResponseEntity<Response<Object>> refreshToken(
            @RequestBody RequestTokenModel requestTokenModel
    ) {
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, authorizerService.refreshToken(requestTokenModel))
        );
    }

}
