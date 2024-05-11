package com.fusm.servicebroker.servicebroker.model.ms_settings;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SettingModel {

    private String description;
    private String value;

}

