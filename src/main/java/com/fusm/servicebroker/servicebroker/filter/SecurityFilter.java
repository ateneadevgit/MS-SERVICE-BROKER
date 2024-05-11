package com.fusm.servicebroker.servicebroker.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_security.BlackListModel;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import com.fusm.servicebroker.servicebroker.util.SharedMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;

@Component
@CrossOrigin
@Order(1)
public class SecurityFilter implements Filter {

    @Autowired
    private IMiddlewareService middlewareService;

    @Autowired
    private SharedMethod sharedMethod;

    ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(httpServletRequest);

        String httpMethod = requestWrapper.getMethod();
        httpServletRequest.setAttribute("methodValue", httpMethod);
        httpServletRequest.setAttribute("path", requestWrapper.getRequestURI());
        httpServletRequest.setAttribute("Authorization", requestWrapper.getHeader("Authorization"));

        if (sharedMethod.validateUri(Arrays.asList(Constant.SECURITY_FILTER_ALLOWED), requestWrapper.getRequestURI())) {

            String errorMessage = objectMapper.writeValueAsString(new Response<>(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.getReasonPhrase()));
            Boolean authorized = true;

            if ("POST".equalsIgnoreCase(httpMethod) || "PUT".equalsIgnoreCase(httpMethod)) {
                String body = new String(requestWrapper.getContentAsByteArray(), requestWrapper.getCharacterEncoding());
                authorized = middlewareService.getSecurityApproval(new BlackListModel(body, false));
                httpServletRequest.setAttribute("body", body);
            }

            if (!authorized) {
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write(errorMessage);
                response.getWriter().flush();
            } else {
                filterChain.doFilter(requestWrapper, servletResponse);
            }
        } else {
            filterChain.doFilter(requestWrapper, servletResponse);
        }

    }

}
