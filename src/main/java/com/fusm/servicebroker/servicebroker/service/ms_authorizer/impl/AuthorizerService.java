package com.fusm.servicebroker.servicebroker.service.ms_authorizer.impl;

import com.fusm.servicebroker.servicebroker.model.ms_authorizer.RequestTokenModel;
import com.fusm.servicebroker.servicebroker.service.ms_authorizer.IAuthorizerService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthorizerService implements IAuthorizerService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-authorizer.complete-path}")
    private String AUTHORIZER_ROUTE;

    @Value("${ms-authorizer.authorizer.path}")
    private String AUTHORIZER_SERVICE;


    @Override
    public Object refreshToken(RequestTokenModel requestTokenModel) {
        return webClientConnector.connectWebClient(AUTHORIZER_ROUTE)
                .post()
                .uri(AUTHORIZER_SERVICE + "/refresh")
                .bodyValue(requestTokenModel)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

}
