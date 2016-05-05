package com.baomidou.springwind.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PjaxInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(PjaxInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        LOGGER.debug(String.format("interceptor: uri=%s",
                request.getRequestURI()));
        String requestURI = request.getRequestURI();
        //js.css.png.json
        if(requestURI.contains(".png") || requestURI.contains(".ico")
                || requestURI.contains(".jpg")){
            return false;
        }
        if (request.getHeader("X-PJAX") == null && request.getMethod().equals("GET")) {
            LOGGER.debug(String.format("interceptor: redirect=%s", request.getContextPath()));
            response.sendRedirect(request.getContextPath());
            return false;
        }
        return super.preHandle(request, response, handler);
    }
}