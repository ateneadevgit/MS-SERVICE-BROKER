package com.fusm.servicebroker.servicebroker.model.ms_settings;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SettingRequest {
    private String settingName;
}
