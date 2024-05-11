package com.fusm.servicebroker.servicebroker.model.ms_program;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryFilterProposal {

    private Integer facultyId;
    private Integer campusId;
    private Integer levelFormation;
    private Integer modalityId;

}
