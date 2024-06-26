package com.heartbeat.myapp.util;

import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtil implements ApplicationContextAware {

    /**
     * spring的应用上下文
     */
    @Getter
    private static ApplicationContext applicationContext;

    /**
     * 初始化时将应用上下文设置进applicationContext
     * @param applicationContext ApplicationContext
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * 根据bean名称获取某个bean对象
     *
     * @param name bean名称
     * @return Object
     */
    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }

    /**
     * 根据bean的class获取某个bean对象
     * @param beanClass Class<T>
     * @return <T>
     */
    public static <T> T getBean(Class<T> beanClass) throws BeansException {
        return applicationContext.getBean(beanClass);
    }

    /**
     * 获取spring.profiles.active
     */
    public static String getProfile(){
        return getApplicationContext().getEnvironment().getActiveProfiles()[0];
    }
}
