package com.fusm.servicebroker.servicebroker.model.ms_login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogOutModel {

    @Email
    @NotEmpty
    @NotNull
    private String email;

}
