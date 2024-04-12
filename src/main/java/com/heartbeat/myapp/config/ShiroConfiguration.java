package com.heartbeat.myapp.config;

import com.heartbeat.myapp.components.shiro.CustomRealm;
import com.heartbeat.myapp.components.shiro.JwtDefaultSubjectFactory;
import com.heartbeat.myapp.components.shiro.JwtFilter;
import jakarta.servlet.Filter;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SubjectFactory;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {

    @Autowired
    private CustomRealm customRealm;

    @Bean
    public SubjectFactory subjectFactory() {
        return new JwtDefaultSubjectFactory();
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        defaultSecurityManager.setRealm(customRealm);

        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        defaultSecurityManager.setSubjectDAO(subjectDAO);
        defaultSecurityManager.setSubjectFactory(subjectFactory());

        return defaultSecurityManager;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        //创建拦截链实例
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        //设置组登录请求，其他路径一律自动跳转到这里
        shiroFilterFactoryBean.setLoginUrl("/login");
        //未授权跳转路径
        shiroFilterFactoryBean.setUnauthorizedUrl("/notRole");
        //设置拦截链map
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //放行请求
        filterChainDefinitionMap.put("/shiro/getToken", "anon");
        //拦截剩下的其他请求
        filterChainDefinitionMap.put("/**", "authc");
        //设置拦截规则给shiro的拦截链工厂
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        // 添加自己的自定义拦截器并且取名为jwt
        Map<String, Filter> filterMap = new HashMap<String, Filter>(1);
        filterMap.put("jwt", new JwtFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        //拦截链配置，从上向下顺序执行，一般将jwt过滤器放在最为下边
        filterChainDefinitionMap.put("/**", "jwt");
        //配置拦截链到过滤器工厂
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        //返回实例
        return shiroFilterFactoryBean;
    }

}
