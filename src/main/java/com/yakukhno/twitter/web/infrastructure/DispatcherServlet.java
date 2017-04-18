package com.yakukhno.twitter.web.infrastructure;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {

    private ConfigurableApplicationContext webContext;

    @Override
    public void init() throws ServletException {
        ConfigurableApplicationContext commonContext
                = (ConfigurableApplicationContext) getServletContext()
                .getAttribute("commonContext");
        String webContextName = getInitParameter("contextConfigLocation");
        webContext = new ClassPathXmlApplicationContext(
                new String[]{webContextName}, commonContext
        );
    }

    @Override
    public void destroy() {
        webContext.close();
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServletException, IOException {
        HandlerMapping handlerMapping = webContext.getBean("handlerMapping", HandlerMapping.class);
        String beanName = handlerMapping.beanNameFromRequest(request);
        handleRequest(beanName, request, response);
    }

    private void handleRequest(String beanName,
                               HttpServletRequest request,
                               HttpServletResponse response) throws IOException {
        MyController controller = (MyController) webContext.getBean(beanName);
        if (controller != null) {
            controller.handleRequest(request, response);
        }
    }

//    private String getBeanNameFromURI(HttpServletRequest request) {
//        String requestURI = request.getRequestURI();
//        return requestURI.substring(requestURI.lastIndexOf('/'));
//    }
}
