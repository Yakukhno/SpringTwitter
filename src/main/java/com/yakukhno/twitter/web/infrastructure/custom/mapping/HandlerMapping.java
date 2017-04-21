package com.yakukhno.twitter.web.infrastructure.custom.mapping;

import javax.servlet.http.HttpServletRequest;

public interface HandlerMapping {
    String beanNameFromRequest(HttpServletRequest request);
}
