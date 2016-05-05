package com.baomidou.springwind.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.baomidou.kisso.common.util.HttpUtil;

public class PjaxInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(PjaxInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        LOGGER.debug(String.format("interceptor: uri=%s", request.getRequestURI()));
        if (request.getHeader("X-PJAX") == null && !HttpUtil.isAjax(request)) {
            response.sendRedirect("/index.html");
            return false;
        }
        return true;
    }
}