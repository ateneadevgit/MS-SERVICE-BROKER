package com.fusm.servicebroker.servicebroker.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_authorizer.AuthUserModel;
import com.fusm.servicebroker.servicebroker.model.ms_safety_mesh.ValidatePermission;
import com.fusm.servicebroker.servicebroker.model.ms_security.BlackListModel;
import com.fusm.servicebroker.servicebroker.model.ms_settings.SettingRequest;
import com.fusm.servicebroker.servicebroker.service.ms_settings.ISettingsService;
import com.fusm.servicebroker.servicebroker.util.Constant;
import com.fusm.servicebroker.servicebroker.util.SharedMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
@CrossOrigin
@Order(3)
public class SafetyMeshFilter implements Filter {

    @Autowired
    private IMiddlewareService middlewareService;

    @Autowired
    private ISettingsService settingsService;

    @Autowired
    private SharedMethod sharedMethod;

    ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (sharedMethod.validateUri(Arrays.asList(Constant.SAFETY_MESH_FILTER_ALLOWED), request.getRequestURI())) {
            String errorMessage = objectMapper.writeValueAsString(new Response<>(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.getReasonPhrase()));
            String pathValue = (String) request.getAttribute("path");
            String[] path = pathValue.split("/");
            Integer moduleId = getSettingValue(path[4].toUpperCase());
            String userData = (String) request.getAttribute("userData");
            AuthUserModel user = objectMapper.readValue(userData, AuthUserModel.class);

            String httpMethod = (String) request.getAttribute("methodValue");
            Boolean authorized = false;

            if ("GET".equalsIgnoreCase(httpMethod)) authorized = middlewareService.hasView(new ValidatePermission(user.getRole(), moduleId));
            if ("POST".equalsIgnoreCase(httpMethod)) authorized = middlewareService.hasWrite(new ValidatePermission(user.getRole(), moduleId));
            if ("PUT".equalsIgnoreCase(httpMethod)) authorized = middlewareService.hasUpdate(new ValidatePermission(user.getRole(), moduleId));
            if ("DELETE".equalsIgnoreCase(httpMethod)) authorized = middlewareService.hasDelete(new ValidatePermission(user.getRole(), moduleId));

            if (!authorized) {
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write(errorMessage);
                response.getWriter().flush();
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }

        }else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    private Integer getSettingValue(String settingName) {
        String settingValue = settingsService.getSetting(new SettingRequest(settingName));
        return (settingValue == null) ? null : Integer.parseInt(settingValue);
    }

}
