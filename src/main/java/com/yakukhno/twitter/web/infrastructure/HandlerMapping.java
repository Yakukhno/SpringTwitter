package com.yakukhno.twitter.web.infrastructure;

import javax.servlet.http.HttpServletRequest;

public interface HandlerMapping {
    String beanNameFromRequest(HttpServletRequest request);
}
