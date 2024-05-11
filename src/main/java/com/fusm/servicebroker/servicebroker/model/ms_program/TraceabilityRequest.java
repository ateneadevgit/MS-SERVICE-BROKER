package com.fusm.servicebroker.servicebroker.model.ms_program;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraceabilityRequest {

    private Date proposalAprovedDate;
    private MinuteRequest superiorCouncilMinute;
    private Date menEndDate;
    private MinuteRequest viceAcademicMinute;
    private Date nsacesDate;
    private String sinies;
    private MinuteRequest academicCouncilMinute;
    private List<CampusRequest> campus;
    private FileModel approvedMinute;
    private String createdBy;
    private Integer roleId;

}
