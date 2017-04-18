package com.yakukhno.twitter.web.infrastructure;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class HelloController {

    @RequestMapping("/hello")
    public void handleRequest(HttpServletRequest request,
                              HttpServletResponse response)
            throws IOException {
        try (PrintWriter out = response.getWriter()) {
            out.println("<b>Hello</b>");
        }
    }
}
