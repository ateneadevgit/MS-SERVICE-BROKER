package com.fusm.servicebroker.servicebroker.model.ms_program;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProposalUpgradeRequest {

    @NotNull
    private List<Integer> modulesId;

    @NotNull
    private FileModel minute;

    @NotNull
    private Boolean hasApproval;

    private String createdBy;
    private Integer roleId;

}

