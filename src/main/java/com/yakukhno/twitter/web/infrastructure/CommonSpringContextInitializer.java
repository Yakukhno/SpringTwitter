package com.yakukhno.twitter.web.infrastructure;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class CommonSpringContextInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        String[] springContexts = getCommonContextConfigLocations(servletContext);
        ConfigurableApplicationContext springContext
                = new ClassPathXmlApplicationContext(springContexts);
        servletContext.setAttribute("commonContext", springContext);
    }

    private String[] getCommonContextConfigLocations(ServletContext servletContext) {
        return servletContext.getInitParameter("commonContextConfigLocation")
                .split(" ");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {}
}
