package com.fusm.servicebroker.servicebroker.controller.ms_notification;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_notification.TemplateRequest;
import com.fusm.servicebroker.servicebroker.service.ms_notification.ITemplateService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Clase que expone los servicios relacionados con las plantillas de las notificaciones
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.PUBLIC_ROUTE + AppRoutes.TEMPLATE_ROUTE)
public class TemplateController {

    @Autowired
    private ITemplateService templateService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Obntiene la plantilla de la notificaicón
     * @param templateId ID de la plantilla
     * @return plantilla
     */
    @GetMapping("/{id}")
    public ResponseEntity<Response<Object>> getTemplate(
            @PathVariable("id") Integer templateId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.TEMPLATE_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, templateService.getTemplate(templateId))
        );
    }

    /**
     * Obtiene una lista de todas las plantillas
     * @return lista de plantillas
     */
    @GetMapping
    public ResponseEntity<Response<Object>> getAllTemplates(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.TEMPLATE_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, templateService.getTemplates())
        );
    }

    /**
     * Actualiza la plantilla
     * @param templateId ID de la plantilla
     * @param templateRequest Modelo que contiene la información de la plantilla que se desea actualizar
     * @return OK
     */
    @PutMapping("/{id}")
    public ResponseEntity<Response<String>> updateTemplate(
            @PathVariable("id") Integer templateId,
            @RequestBody TemplateRequest templateRequest,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(templateRequest.toString(), Constant.PUT_METHOD, false, Constant.TEMPLATE_MODULE, authorizationHeader);
        templateService.updateTemplate(templateRequest, templateId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

}
