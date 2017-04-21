package com.yakukhno.twitter.web.infrastructure.custom;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class CommonSpringContextInitializer implements ServletContextListener {

    private ConfigurableApplicationContext springContext;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        String[] springContexts = getCommonContextConfigLocations(servletContext);
        springContext = new ClassPathXmlApplicationContext(springContexts);
        servletContext.setAttribute("commonContext", springContext);
    }

    private String[] getCommonContextConfigLocations(ServletContext servletContext) {
        return servletContext.getInitParameter("commonContextConfigLocation")
                .split(" ");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        springContext.close();
    }
}
