package com.fusm.servicebroker.servicebroker.model.ms_program;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProgramDto {

    private Integer idProgram;
    private String name;
    private String logo;
    private Integer idFaculty;
    private Integer idLevelFormation;
    private String fileUrl;
    private String createdAt;
    private Integer idStatus;
    private String campusList;

}