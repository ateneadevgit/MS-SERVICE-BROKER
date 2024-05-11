package com.fusm.servicebroker.servicebroker.service.ms_login.impl;

import com.fusm.servicebroker.servicebroker.exception.GlobalCustomException;
import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.ms_authorizer.TokenModel;
import com.fusm.servicebroker.servicebroker.model.ms_login.LogInModel;
import com.fusm.servicebroker.servicebroker.model.ms_login.LogOutModel;
import com.fusm.servicebroker.servicebroker.model.ms_login.UserModel;
import com.fusm.servicebroker.servicebroker.model.ms_security.BlackListModel;
import com.fusm.servicebroker.servicebroker.service.ms_login.ILogInService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.apache.el.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class LogInService implements ILogInService {

    @Autowired
    private IMiddlewareService middlewareService;

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-login.complete-path}")
    private String LOGIN_ROUTE;

    @Value("${ms-login.login.path}")
    private String LOGIN_SERVICE;

    @Value("${ms-authorizer.complete-path}")
    private String AUTHORIZER_ROUTE;

    @Value("${ms-authorizer.authorizer.path}")
    private String AUTHORIZER_SERVICE;


    @Override
    public TokenModel logIn(LogInModel logInModel) {
        UserModel user = webClientConnector.connectWebClient(LOGIN_ROUTE)
                .post()
                .uri(LOGIN_SERVICE)
                .bodyValue(logInModel)
                .retrieve()
                .bodyToMono(UserModel.class)
                .block();
        if (user == null) throw new GlobalCustomException(HttpStatus.FORBIDDEN.getReasonPhrase(), HttpStatus.FORBIDDEN);
        user.setPassword(null);
        TokenModel token = webClientConnector.connectWebClient(AUTHORIZER_ROUTE)
                .post()
                .uri(AUTHORIZER_SERVICE + "/create")
                .bodyValue(user)
                .retrieve()
                .bodyToMono(TokenModel.class)
                .block();
        token.setUserData(user);
        return token;
    }

    @Override
    public void logOut(LogOutModel logOutModel) {
        webClientConnector.connectWebClient(AUTHORIZER_ROUTE)
                .post()
                .uri(AUTHORIZER_SERVICE + "/kill")
                .bodyValue(logOutModel)
                .retrieve()
                .bodyToMono(TokenModel.class)
                .block();
    }

}
