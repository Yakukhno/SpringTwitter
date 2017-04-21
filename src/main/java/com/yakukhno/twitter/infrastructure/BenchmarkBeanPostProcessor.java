package com.yakukhno.twitter.infrastructure;

import com.yakukhno.twitter.infrastructure.custom.BenchmarkInvocationHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BenchmarkBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> clazz = bean.getClass();
        for (Method method : clazz.getMethods()) {
            if (method.isAnnotationPresent(Benchmark.class)) {
                bean = Proxy.newProxyInstance(clazz.getClassLoader(),
                        clazz.getInterfaces(),
                        new BenchmarkInvocationHandler(bean));
                break;
            }
        }
        return bean;
    }
}
