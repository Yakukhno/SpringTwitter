package com.yakukhno.twitter.web.infrastructure.custom.mapping;

import javax.servlet.http.HttpServletRequest;

public class BeanNameURLHandlerMapping implements HandlerMapping {
    @Override
    public String beanNameFromRequest(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return requestURI.substring(requestURI.lastIndexOf('/'));
    }
}
