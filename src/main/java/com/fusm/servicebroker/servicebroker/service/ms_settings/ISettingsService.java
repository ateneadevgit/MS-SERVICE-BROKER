package com.fusm.servicebroker.servicebroker.service.ms_settings;

import com.fusm.servicebroker.servicebroker.model.ms_settings.SettingModel;
import com.fusm.servicebroker.servicebroker.model.ms_settings.SettingRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ISettingsService {
    String getSetting(SettingRequest settingRequest);
    Object getSettings();
    void updateSetting(SettingModel settingModel, Integer settingId);

}
