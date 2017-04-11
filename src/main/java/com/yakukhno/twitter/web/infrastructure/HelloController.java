package com.yakukhno.twitter.web.infrastructure;

import org.springframework.beans.factory.BeanNameAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloController implements MyController, BeanNameAware {

    private String beanName;

    @Override
    public void handleRequest(HttpServletRequest request,
                              HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            out.println("<b>Hello</b>");
            out.println("<b>" + beanName + "</b>");
        }
    }

    @Override
    public void setBeanName(String s) {
        this.beanName = s;
    }
}
