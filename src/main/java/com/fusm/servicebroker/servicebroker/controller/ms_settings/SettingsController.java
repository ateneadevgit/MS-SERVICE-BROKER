package com.fusm.servicebroker.servicebroker.controller.ms_settings;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_settings.SettingModel;
import com.fusm.servicebroker.servicebroker.model.ms_settings.SettingRequest;
import com.fusm.servicebroker.servicebroker.service.ms_settings.ISettingsService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * Clase que expone los servicios relacionados con los valores parametricos de la aplicación
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.PUBLIC_ROUTE + AppRoutes.SETTINGS_ROUTE)
public class SettingsController {

    @Autowired
    private ISettingsService settingsService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Obtiene el valor de una configuración
     * @param settingRq Modelo que contiene la configuración a buscar
     * @return valor de la configuraicón
     */
    @PostMapping
    public ResponseEntity<Response<String>> getSettingValue(
            @RequestBody SettingRequest settingRq
    ) {
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, settingsService.getSetting(settingRq))
        );
    }

    /**
     * Obtiene una lista de todas las configuraciones
     * @return lista de configuraciones
     */
    @GetMapping
    public ResponseEntity<Response<Object>> getSettings(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.SETTIGNS_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, settingsService.getSettings())
        );
    }

    /**
     * Actualiza la configuración
     * @param settingId ID de la configuración
     * @param settingModel Modelo que contiene la información a actualizar de la configuración
     * @return OK
     */
    @PutMapping("/{id}")
    public ResponseEntity<Response<String>> updateSetting(
            @PathVariable("id") Integer settingId,
            @RequestBody SettingModel settingModel,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(settingModel.toString(), Constant.PUT_METHOD, false, Constant.SETTIGNS_MODULE, authorizationHeader);
        settingsService.updateSetting(settingModel, settingId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

}
