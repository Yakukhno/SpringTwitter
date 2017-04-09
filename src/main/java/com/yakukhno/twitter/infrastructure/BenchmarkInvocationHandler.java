package com.yakukhno.twitter.infrastructure;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class BenchmarkInvocationHandler implements InvocationHandler {
    private Object object;

    public BenchmarkInvocationHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object res;
        Method originalMethod = object.getClass()
                .getMethod(method.getName(), method.getParameterTypes());
        Benchmark benchmark = originalMethod.getAnnotation(Benchmark.class);
        if (benchmark != null && benchmark.value()) {
            long startTime = System.nanoTime();
            res = method.invoke(object, args);
            long finishTime = System.nanoTime() - startTime;
            System.out.println(getFormattedString(method, finishTime));
        } else {
            res = method.invoke(object, args);
        }
        return res;
    }

    private String getFormattedString(Method method, long finishTime) {
        return String.format("%s.%s() %dns", object.getClass().getSimpleName(),
                method.getName(), finishTime);
    }
}
