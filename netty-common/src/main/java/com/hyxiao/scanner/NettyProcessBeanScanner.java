package com.hyxiao.scanner;

import com.hyxiao.annotation.Cmd;
import com.hyxiao.annotation.Module;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class NettyProcessBeanScanner implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //  1.首先获取当前bean的class类型
        Class<?> clazz = bean.getClass();
        boolean isPresent = clazz.isAnnotationPresent(Module.class);

        if (isPresent) {
            String moduleName = clazz.getAnnotation(Module.class).module();
            Method[] methods = clazz.getMethods();
            if (methods != null && methods.length > 0) {
                for (Method method : methods) {
                    // 2.获取方法上的注解
                    boolean isMethodPresent = method.isAnnotationPresent(Cmd.class);
                    if (isMethodPresent) {
                        String cmdName = method.getAnnotation(Cmd.class).cmd();
                        System.out.println("cmd: " + cmdName);
                    }
                }
            }
        }

        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
