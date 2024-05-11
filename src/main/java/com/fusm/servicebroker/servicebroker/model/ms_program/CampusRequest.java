package com.fusm.servicebroker.servicebroker.model.ms_program;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampusRequest {

    @NotNull
    private Integer campusId;

    @NotNull
    @NotEmpty
    private String resolution;

    @NotNull
    private Date resolutionDate;

}

