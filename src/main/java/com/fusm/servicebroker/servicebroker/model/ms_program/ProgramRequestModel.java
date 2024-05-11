package com.fusm.servicebroker.servicebroker.model.ms_program;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProgramRequestModel {

    private FileModel logo;

    private FileModel cover;

    @NotEmpty
    @NotNull
    @Size(min = 1, max = 199)
    private String programName;

    @NotNull
    private Integer facultyId;

    @NotNull
    private Integer formationTypeId;

    @NotNull
    private Integer[] campus;

    @NotNull
    private Integer formationLevel;

    @NotNull
    private Integer[] modality;

    @NotNull
    private String developmentDate;

    @NotNull
    private FileModel fileProposal;

    @NotNull
    private Boolean isEnlarge;

    private Integer programFather;

    @NotNull
    private Integer registryTypeId;

    private String createdBy;

    private Integer roleId;

}
