package com.fusm.servicebroker.servicebroker.model.ms_workflow;

import com.fusm.servicebroker.servicebroker.model.ms_program.FileModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecondLanguageRequest {

    private FileModel cover;
    private String tittle;
    private String startLevel;
    private String endLevel;
    private Integer groupId;
    private FileModel icon;
    private String description;
    private Integer modalityId;
    private Integer duration;
    private Integer hours;
    private String inscriptionLink;
    private String createdBy;

}
