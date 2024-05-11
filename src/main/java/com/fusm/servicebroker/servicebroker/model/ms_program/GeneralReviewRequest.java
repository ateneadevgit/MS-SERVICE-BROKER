package com.fusm.servicebroker.servicebroker.model.ms_program;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralReviewRequest {

    private String review;
    private String createdBy;
    private Integer roleId;
    private Integer replyTo;
    private Integer objectId;
    private String objectType;

}
