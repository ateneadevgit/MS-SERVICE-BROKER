package com.fusm.servicebroker.servicebroker.model;

import com.fusm.servicebroker.servicebroker.model.ms_authorizer.AuthUserModel;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalData {

    private AuthUserModel authUserModel;

}
