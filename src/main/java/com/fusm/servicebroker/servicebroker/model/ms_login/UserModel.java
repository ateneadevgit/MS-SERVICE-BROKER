package com.fusm.servicebroker.servicebroker.model.ms_login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    private String email;
    private String password;
    private String name;
    private Integer faculty;
    private Integer role;
    private Integer[] programId;

}