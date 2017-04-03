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

        Class<?> type = config.getImpl(beanName);
        if (type == null) {
            throw new RuntimeException("Bean not found!");
        }

        BeanBuilder builder = new BeanBuilder(type);
        builder.createBean();
        builder.callPostConstructMethod();
        builder.callInitMethod();
        builder.createBeanProxy();
        bean = (T) builder.build();

        beans.put(beanName, bean);
        return bean;
    }

    private class BeanBuilder {
        private final Class<?> type;
        private Object bean;

        private BeanBuilder(Class<?> type) {
            this.type = type;
        }

        public void createBean() throws Exception {
            if (type.getConstructors().length != 1) {
                throw new RuntimeException("Only one constructor is allowed");
            }

            Constructor<?> constructor = getConstructor();
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            if (parameterTypes.length != 0) {
                List<Class> args = new ArrayList<>(parameterTypes.length);
                for (Class<?> argClass : parameterTypes) {
                    String argBeanName = getBeanNameByType(argClass);
                    args.add(getBean(argBeanName));
                }
                bean = constructor.newInstance(args.toArray());
            } else {
                bean = type.newInstance();
            }
        }

        private Constructor<?> getConstructor() {
            return type.getConstructors()[0];
        }

        private String getBeanNameByType(Class<?> argClass) {
            String argName = argClass.getSimpleName();
            return argName.substring(0, 1).toLowerCase()
                    + argName.substring(1);
        }

        public void callPostConstructMethod() throws Exception {
            Class<?> clazz = bean.getClass();
            for (Method method : clazz.getMethods()) {
                if (method.isAnnotationPresent(PostConstructBean.class)) {
                    method.invoke(bean);
                }
            }
        }

        public void callInitMethod() throws Exception {
            Class<?> clazz = bean.getClass();
            Method method;
            try {
                method = clazz.getMethod("init");
            } catch (NoSuchMethodException ex) {
                return;
            }
            method.invoke(bean);
        }

        public void createBeanProxy() {
            Class<?> clazz = bean.getClass();
            for (Method method : clazz.getMethods()) {
                if (method.isAnnotationPresent(Benchmark.class)) {
                    bean = Proxy.newProxyInstance(clazz.getClassLoader(),
                            clazz.getInterfaces(),
                            new BenchmarkInvocationHandler(bean));
                    break;
                }
            }
        }

        public Object build() {
            return bean;
        }
    }
}
