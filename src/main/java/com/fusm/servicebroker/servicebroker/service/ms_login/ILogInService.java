package com.fusm.servicebroker.servicebroker.service.ms_login;

import com.fusm.servicebroker.servicebroker.model.ms_authorizer.TokenModel;
import com.fusm.servicebroker.servicebroker.model.ms_login.LogInModel;
import com.fusm.servicebroker.servicebroker.model.ms_login.LogOutModel;
import org.springframework.stereotype.Service;

@Service
public interface ILogInService {
    TokenModel logIn(LogInModel logInModel);
    void logOut(LogOutModel logOutModel);
}
