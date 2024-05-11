package com.fusm.servicebroker.servicebroker.model.ms_authorizer;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserModel {

    private String email;
    private String password;
    private String name;
    private Integer faculty;
    private Integer role;
    private Integer[] programId;

}
