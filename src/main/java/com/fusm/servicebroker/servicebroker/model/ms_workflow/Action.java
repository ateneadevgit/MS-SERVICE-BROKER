package com.fusm.servicebroker.servicebroker.model.ms_workflow;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Action {

    private Integer actionId;
    private String actionName;
    private String description;

}
