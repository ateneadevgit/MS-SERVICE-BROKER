package com.fusm.servicebroker.servicebroker.controller.ms_events;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_events.EventGeneralRequest;
import com.fusm.servicebroker.servicebroker.model.ms_events.EventSearch;
import com.fusm.servicebroker.servicebroker.service.ms_events.IEventService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Clase que expone todos los servicios relacionados con los eventos del calendario
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.PUBLIC_ROUTE + AppRoutes.EVENT_ROUTE)
public class EventController {

    @Autowired
    private IEventService eventService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Crea un evento general
     * @param eventRequest Modelo que contiene la información para crear un evento general
     * @return OK
     */
    @PostMapping("/general")
    public ResponseEntity<Response<String>> createGeneralEvent(
            @RequestBody EventGeneralRequest eventRequest,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(eventRequest.toString(), Constant.POST_METHOD, false, Constant.EVENT_GENERAL_MODULE, authorizationHeader);
        eventService.createGeneralEvent(eventRequest);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Crea un evento personal
     * @param eventRequest Modelo que contiene la información para crear un evento personal
     * @return OK
     */
    @PostMapping("/personal")
    public ResponseEntity<Response<String>> createPersonalEvent(
            @RequestBody EventGeneralRequest eventRequest,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(eventRequest.toString(), Constant.POST_METHOD, false, Constant.EVENT_PERSONAL_MODULE, authorizationHeader);
        eventService.createPersonalEvent(eventRequest);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Obtiene la lista de eventos
     * @param eventSearch Modelo que contiene los parámetros de búsqueda para realizar filtrados
     * @return lista de eventos
     */
    @PostMapping("/get")
    public ResponseEntity<Response<Object>> getEvents(
            @RequestBody EventSearch eventSearch,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.EVENT_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, eventService.getEvents(eventSearch))
        );
    }

    /**
     * Actualiza un evento
     * @param eventId ID del evento
     * @param isPersonal Identifica si el evento es personal o no mediante un TRUE o FALSE
     * @param eventGeneralRequest Modelo que contiene la información a actualizar del evento
     * @return OK
     */
    @PutMapping("/{id}/is-personal/{personal}")
    public ResponseEntity<Response<String>> updateEvent(
            @PathVariable("id") Integer eventId,
            @PathVariable("personal") Boolean isPersonal,
            @RequestBody EventGeneralRequest eventGeneralRequest,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(eventGeneralRequest.toString(), Constant.PUT_METHOD, false, Constant.EVENT_MODULE, authorizationHeader);
        eventService.updateGeneralEvent(eventGeneralRequest, eventId, isPersonal);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Deshabilita un evento
     * @param eventId ID del evento
     * @param isPersonal Identifica si el evento es personal o no mediante un TRUE o FALSE
     * @return OK
     */
    @DeleteMapping("/{id}/is-personal/{personal}")
    public ResponseEntity<Response<String>> disableEvent(
            @PathVariable("id") Integer eventId,
            @PathVariable("personal") Boolean isPersonal,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.PUT_METHOD, false, Constant.EVENT_MODULE, authorizationHeader);
        eventService.disableEvent(eventId, isPersonal);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

}
