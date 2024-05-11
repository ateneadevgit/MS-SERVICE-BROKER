package com.fusm.servicebroker.servicebroker.middleware.impl;

import com.fusm.servicebroker.servicebroker.exception.GlobalCustomException;
import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.ms_authorizer.AuthUserModel;
import com.fusm.servicebroker.servicebroker.model.ms_authorizer.RequestTokenModel;
import com.fusm.servicebroker.servicebroker.model.ms_safety_mesh.ValidatePermission;
import com.fusm.servicebroker.servicebroker.model.ms_security.BlackListModel;
import com.fusm.servicebroker.servicebroker.model.ms_settings.SettingRequest;
import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.service.ms_settings.ISettingsService;
import com.fusm.servicebroker.servicebroker.util.Constant;
import com.fusm.servicebroker.servicebroker.util.SharedMethod;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class MiddlewareService implements IMiddlewareService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Autowired
    private ISettingsService settingsService;

    @Autowired
    private GlobalService globalService;

    @Value("${ms-security.complete-path}")
    private String SECURITY_ROUTE;

    @Value("${ms-security.security.path}")
    private String SECURITY_SERVICE;

    @Value("${ms-authorizer.complete-path}")
    private String AUTHORIZER_ROUTE;

    @Value("${ms-authorizer.authorizer.path}")
    private String AUTHORIZER_SERVICE;

    @Value("${ms-safety-mesh.complete-path}")
    private String SAFETY_MESH_ROUTE;

    @Value("${ms-safety-mesh.permission.path}")
    private String SAFETY_MESH_SERVICE;

    Logger logger = LoggerFactory.getLogger(MiddlewareService.class);


    @Override
    public Boolean getSecurityApproval(BlackListModel blackListModel) {
        Boolean authorized = webClientConnector.connectWebClient(SECURITY_ROUTE)
                .post()
                .uri(SECURITY_SERVICE)
                .bodyValue(blackListModel)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
        if (Boolean.FALSE.equals(authorized)) {
            logger.info("Middleware code inject not passed :::: User Unauthorized");
            throw new GlobalCustomException(HttpStatus.FORBIDDEN.getReasonPhrase(), HttpStatus.FORBIDDEN);
        }
        return authorized;
    }

    @Override
    public AuthUserModel getAuthorizerApproval(RequestTokenModel requestTokenModel) {
        AuthUserModel userModel = null;
        try {
            userModel =  webClientConnector.connectWebClient(AUTHORIZER_ROUTE)
                    .post()
                    .uri(AUTHORIZER_SERVICE + "/validate")
                    .bodyValue(requestTokenModel)
                    .retrieve()
                    .bodyToMono(AuthUserModel.class)
                    .block();
        } catch (WebClientResponseException ex) {
            HttpStatus statusCode = ex.getStatusCode();
            if (statusCode.value() == HttpStatus.NOT_ACCEPTABLE.value()) {
                logger.info("Middleware of token validation not passed :::: User NOT_ACCEPTABLE");
                throw new GlobalCustomException(statusCode.getReasonPhrase(), statusCode);
            }

            if (statusCode.value() == HttpStatus.UNAUTHORIZED.value()) {
                logger.info("Middleware of token validation not passed :::: User Unauthorized");
                throw new GlobalCustomException(statusCode.getReasonPhrase(), statusCode);
            }

            logger.info("Middleware of token validation not passed :::: Error service validate token");
            throw new GlobalCustomException(HttpStatus.FORBIDDEN.getReasonPhrase(), HttpStatus.FORBIDDEN);
        } catch (Exception ex){
            logger.info("Middleware of token validation not passed :::: Error service validate token");
            throw new GlobalCustomException(HttpStatus.FORBIDDEN.getReasonPhrase(), HttpStatus.FORBIDDEN);
        }

        globalService.modifyGlobalUserData(userModel);
        return userModel;
    }

    @Override
    public Boolean hasView(ValidatePermission validatePermission) {
        Boolean authorized = webClientConnector.connectWebClient(SAFETY_MESH_ROUTE)
                .post()
                .uri(SAFETY_MESH_SERVICE + "/has-view")
                .bodyValue(validatePermission)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
        if (Boolean.FALSE.equals(authorized)) {
            logger.info("Middleware (Has View) not passed :::: User Unauthorized");
            throw new GlobalCustomException(HttpStatus.FORBIDDEN.getReasonPhrase(), HttpStatus.FORBIDDEN);
        }
        return authorized;
    }

    @Override
    public Boolean hasWrite(ValidatePermission validatePermission) {
        Boolean authorized = webClientConnector.connectWebClient(SAFETY_MESH_ROUTE)
                .post()
                .uri(SAFETY_MESH_SERVICE + "/has-write")
                .bodyValue(validatePermission)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
        if (Boolean.FALSE.equals(authorized)) {
            logger.info("Middleware (Has Write) not passed :::: User Unauthorized");
            throw new GlobalCustomException(HttpStatus.FORBIDDEN.getReasonPhrase(), HttpStatus.FORBIDDEN);
        }
        return authorized;
    }

    @Override
    public Boolean hasUpdate(ValidatePermission validatePermission) {
        Boolean authorized = webClientConnector.connectWebClient(SAFETY_MESH_ROUTE)
                .post()
                .uri(SAFETY_MESH_SERVICE + "/has-update")
                .bodyValue(validatePermission)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
        if (Boolean.FALSE.equals(authorized)) {
            logger.info("Middleware (Has Update) not passed :::: User Unauthorized");
            throw new GlobalCustomException(HttpStatus.FORBIDDEN.getReasonPhrase(), HttpStatus.FORBIDDEN);
        }
        return authorized;
    }

    @Override
    public Boolean hasDelete(ValidatePermission validatePermission) {
        Boolean authorized = webClientConnector.connectWebClient(SAFETY_MESH_ROUTE)
                .post()
                .uri(SAFETY_MESH_SERVICE + "/has-delete")
                .bodyValue(validatePermission)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
        if (Boolean.FALSE.equals(authorized)) {
            logger.info("Middleware (Has Delete) not passed :::: User Unauthorized");
            throw new GlobalCustomException(HttpStatus.FORBIDDEN.getReasonPhrase(), HttpStatus.FORBIDDEN);
        }
        return authorized;
    }

    @Override
    public void createLog(String request, String response) {
        logger.info("Service excecuted, with request { " + request + " } " +
                "and response { " + response + " }");
    }
    @Override
    public void allMiddlewares(String request, String method, boolean isHtml, String module, String token) {
        logger.info("Middleware validation with request {" + request + "} and method {" + method + "} called on {" + module + "} module");
        if (method.equals(Constant.POST_METHOD) || method.equals(Constant.PUT_METHOD))
            getSecurityApproval(new BlackListModel(request, isHtml));
        if (token == null) throw new GlobalCustomException(HttpStatus.FORBIDDEN.getReasonPhrase(), HttpStatus.FORBIDDEN);
        token = token.replace("Bearer ", "");
        AuthUserModel user = getAuthorizerApproval(new RequestTokenModel(token));
        if (method.equals(Constant.GET_METHOD)) hasView(new ValidatePermission(user.getRole(), getSettingValue(module.toUpperCase())));
        if (method.equals(Constant.POST_METHOD)) hasWrite(new ValidatePermission(user.getRole(), getSettingValue(module.toUpperCase())));
        if (method.equals(Constant.PUT_METHOD)) hasUpdate(new ValidatePermission(user.getRole(), getSettingValue(module.toUpperCase())));
        if (method.equals(Constant.DELETE_METHOD)) hasDelete(new ValidatePermission(user.getRole(), getSettingValue(module.toUpperCase())));
        logger.info("All middleware validation passed");
    }

    private Integer getSettingValue(String settingName) {
        String settingValue = settingsService.getSetting(new SettingRequest(settingName));
        return (settingValue == null) ? null : Integer.parseInt(settingValue);
    }

}
