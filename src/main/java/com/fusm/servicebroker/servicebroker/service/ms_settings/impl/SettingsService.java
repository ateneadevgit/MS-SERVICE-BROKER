package com.fusm.servicebroker.servicebroker.service.ms_settings.impl;

import com.fusm.servicebroker.servicebroker.model.ms_settings.SettingModel;
import com.fusm.servicebroker.servicebroker.model.ms_settings.SettingRequest;
import com.fusm.servicebroker.servicebroker.service.ms_settings.ISettingsService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SettingsService implements ISettingsService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-settings.complete-path}")
    private String SETTINGS_ROUTE;

    @Value("${ms-settings.path}")
    private String SETTINGS_SERVICE;

    @Override
    public String getSetting(SettingRequest settingRequest) {
        return webClientConnector.connectWebClient(SETTINGS_ROUTE)
                .post()
                .uri(SETTINGS_SERVICE)
                .bodyValue(settingRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Object getSettings() {
        return webClientConnector.connectWebClient(SETTINGS_ROUTE)
                .get()
                .uri(SETTINGS_SERVICE)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public void updateSetting(SettingModel settingModel, Integer settingId) {
        webClientConnector.connectWebClient(SETTINGS_ROUTE)
                .put()
                .uri(SETTINGS_SERVICE + "/" + settingId)
                .bodyValue(settingModel)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
