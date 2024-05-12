package com.hyxiao.scanner;

import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Data
public class Invoker {

    private Method method;

    private Object target;

    /**
     * 创建一个Invoker
     * @param method
     * @param target
     * @return
     */
    public static Invoker createInvoker(Method method, Object target) {
        Invoker invoker = new Invoker();
        invoker.setMethod(method);
        invoker.setTarget(target);
        return invoker;
    }

    public Object invoke(Object... params) {
        try {
            return method.invoke(target, params);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

}
