package com.fusm.servicebroker.servicebroker.model.ms_authorizer;

import com.fusm.servicebroker.servicebroker.model.ms_login.UserModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenModel {

    private String token;
    private String tokenType;
    private Date expiresIn;
    private Date issuedAt;
    private UserModel userData;

}

