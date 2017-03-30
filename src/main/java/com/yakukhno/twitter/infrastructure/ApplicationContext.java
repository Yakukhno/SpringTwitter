package com.yakukhno.twitter.infrastructure;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

public class ApplicationContext implements Context {
    private final Config config;
    private final Map<String, Object> beans = new HashMap<>();

    public ApplicationContext(Config config) {
        this.config = config;
    }

    @Override
    public <T> T getBean(String beanName) throws Exception {
        T bean = (T) beans.get(beanName);

        if (bean != null) {
            return bean;
        }

        Class<?> clazz = config.getImpl(beanName);
        if (clazz == null) {
            throw new RuntimeException("Bean not found!");
        }

        if (clazz.getConstructors().length != 1) {
            throw new RuntimeException("Only one constructor is allowed");
        }

        Constructor<?> constructor = clazz.getConstructors()[0];
        if (constructor.getParameterTypes().length != 0) {
            List<Class> args = new ArrayList<>();
            for (Class<?> argClass : constructor.getParameterTypes()) {
                String argName = argClass.getSimpleName();
                String argBeanName = argName.substring(0, 1).toLowerCase()
                        + argName.substring(1);
                args.add(getBean(argBeanName));
            }
            bean = (T) constructor.newInstance(args.toArray());
        } else {
            bean = (T) clazz.newInstance();
        }

        callPostConstructBean(bean);
        callInitMethod(bean);
        bean = createProxy(bean);

        beans.put(beanName, bean);
        return bean;
    }

    private <T> T createProxy(T bean) {
        Class<?> clazz = bean.getClass();
        for (Method method : clazz.getMethods()) {
            if (method.isAnnotationPresent(Benchmark.class)) {
                bean = (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                        clazz.getInterfaces(),
                        new BenchmarkInvocationHandler(bean));
                break;
            }
        }
        return bean;
    }

    private <T> void callInitMethod(T bean) throws Exception {
        Class<?> clazz = bean.getClass();
        Method method;
        try {
            method = clazz.getMethod("init");
        } catch (NoSuchMethodException ex) {
            return;
        }
        method.invoke(bean);
    }

    private <T> void callPostConstructBean(T bean) throws Exception {
        Class<?> clazz = bean.getClass();
        for (Method method : clazz.getMethods()) {
            if (method.isAnnotationPresent(PostConstructBean.class)) {
                method.invoke(bean);
            }
        }
    }
}
