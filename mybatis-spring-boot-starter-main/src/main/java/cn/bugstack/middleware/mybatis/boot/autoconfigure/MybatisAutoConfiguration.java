package cn.bugstack.middleware.mybatis.boot.autoconfigure;

import cn.bugstack.middleware.mybatis.boot.mybatis.SqlSessionFactory;
import cn.bugstack.middleware.mybatis.boot.mybatis.SqlSessionFactoryBuilder;
import cn.bugstack.middleware.mybatis.boot.spring.MapperFactoryBean;
import cn.bugstack.middleware.mybatis.boot.spring.MapperScannerConfigurer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 */
@Configuration
@ConditionalOnClass({SqlSessionFactory.class})
@EnableConfigurationProperties(MybatisProperties.class)
public class MybatisAutoConfiguration implements InitializingBean {

    @Bean
    @ConditionalOnMissingBean
    public SqlSessionFactory sqlSessionFactory(Connection connection, MybatisProperties mybatisProperties) throws Exception {
        return new SqlSessionFactoryBuilder().build(connection, mybatisProperties.getMapperLocations());
    }

    @Bean
    @ConditionalOnMissingBean
    public Connection connection(MybatisProperties mybatisProperties) {
        try {
            Class.forName(mybatisProperties.getDriver());
            return DriverManager.getConnection(mybatisProperties.getUrl(), mybatisProperties.getUsername(), mybatisProperties.getPassword());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 1. 整体实现结构是以参考mybatis-starter的源码方式实现的
     * 2. 需要在扫描阶段把 BeanDefinitionBuilder 注册到Spring容器中去，便于Spring管理Bean对象初始化等
     * 
     * 自动化配置的加载方式，类似于在spring-config.xml中写这个？
     * <bean class="cn.bugstack.middleware.mybatis.spring.MapperScannerConfigurer">
     *         <!-- 注入sqlSessionFactory -->
     *         <property name="sqlSessionFactory" ref="sqlSessionFactory"/>
     *         <!-- 给出需要扫描Dao接口包 -->
     *         <property name="basePackage" value="cn.bugstack.middleware.mybatis.spring.test.dao"/>
     * </bean>
     */
    public static class AutoConfiguredMapperScannerRegistrar implements EnvironmentAware, ImportBeanDefinitionRegistrar {

        private String basePackage;

        @Override
        public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(MapperScannerConfigurer.class);
            builder.addPropertyValue("basePackage", basePackage);
            registry.registerBeanDefinition(MapperScannerConfigurer.class.getName(), builder.getBeanDefinition());
        }

        @Override
        public void setEnvironment(Environment environment) {
            this.basePackage = environment.getProperty("mybatis.datasource.base-dao-package");
        }
    }

    @Configuration
    @Import(AutoConfiguredMapperScannerRegistrar.class)
    @ConditionalOnMissingBean({MapperFactoryBean.class, MapperScannerConfigurer.class})
    public static class MapperScannerRegistrarNotFoundConfiguration implements InitializingBean {

        @Override
        public void afterPropertiesSet() {
        }

    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }

}
