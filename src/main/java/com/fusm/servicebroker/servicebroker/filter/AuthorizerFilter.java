package com.fusm.servicebroker.servicebroker.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_authorizer.AuthUserModel;
import com.fusm.servicebroker.servicebroker.model.ms_authorizer.RequestTokenModel;
import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.util.Constant;
import com.fusm.servicebroker.servicebroker.util.SharedMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
@CrossOrigin
@Order(2)
public class AuthorizerFilter implements Filter {

    @Autowired
    private IMiddlewareService middlewareService;

    @Autowired
    private GlobalService globalService;

    @Autowired
    private SharedMethod sharedMethod;

    ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (sharedMethod.validateUri(Arrays.asList(Constant.AUTHORIZARION_FILTER_ALLOWED), request.getRequestURI())) {
            String errorMessage = objectMapper.writeValueAsString(new Response<>(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.getReasonPhrase()));
            String token = (String) request.getAttribute("Authorization");
            boolean authorized = false;

            if (token != null) {
                String tokenValue = token.replace("Bearer ", "");
                AuthUserModel userModel = middlewareService.getAuthorizerApproval(new RequestTokenModel(tokenValue));
                if (userModel!= null) {
                    request.setAttribute("userData", objectMapper.writeValueAsString(userModel));
                    globalService.modifyGlobalUserData(userModel);
                    authorized = true;
                }
            }

            if (!authorized) {
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write(errorMessage);
                response.getWriter().flush();
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

}
