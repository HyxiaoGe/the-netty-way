package com.hyxiao.scanner;

import com.hyxiao.annotation.Cmd;
import com.hyxiao.annotation.Module;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * NettyProcessBeanScanner
 * 1. 扫描所有的bean，获取bean上的注解
 * 2. 获取方法上的注解
 * 3. 将方法和bean封装成Invoker
 * 4. 将Invoker注册到InvokerTable中
 * 5. InvokerTable中存储了所有的Invoker
 * 6. 当有消息过来的时候，根据module和cmd找到对应的Invoker，然后执行对应的方法
 * 7. 通过反射执行对应的方法
 * 8. 返回结果
 * 9. 将结果写回到客户端
 * 10. 完成一次调用
 */
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
        //  1.1 判断该类是否有Module注解
        boolean isPresent = clazz.isAnnotationPresent(Module.class);

        if (isPresent) {
            //  1.2 获取Module注解的值
            String moduleName = clazz.getAnnotation(Module.class).module();
            Method[] methods = clazz.getMethods();
            if (methods != null && methods.length > 0) {
                for (Method method : methods) {
                    // 2.获取方法上的注解
                    boolean isMethodPresent = method.isAnnotationPresent(Cmd.class);
                    if (isMethodPresent) {
                        String cmdName = method.getAnnotation(Cmd.class).cmd();
                        if (InvokerTable.getInvoker(moduleName, cmdName) == null) {
                            InvokerTable.addInvoker(moduleName, cmdName, Invoker.createInvoker(method, bean));
                        } else {
                            System.err.println("cmd: " + cmdName + "已经存在");
                        }
                    }
                }
            }
        }

        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
