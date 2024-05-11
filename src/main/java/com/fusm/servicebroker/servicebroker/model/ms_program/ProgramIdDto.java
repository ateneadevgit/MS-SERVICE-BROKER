package com.fusm.servicebroker.servicebroker.model.ms_program;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProgramIdDto {

    private Integer idProgram;
    private String name;
    private String logo;
    private String cover;
    private String developmentDate;
    private Integer idFaculty;
    private Integer idTypeFormation;
    private Integer idLevelFormation;
    private Integer idRegistryType;
    private String fileUrl;
    private String createdAt;
    private String campusList;
    private String modalityList;

}
