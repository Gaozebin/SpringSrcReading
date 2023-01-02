package com.zhouyu;


import com.zhouyu.service.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author gaozebin
 * @date 2023/1/2 16:01
 */
@ComponentScan("com.zhouyu")
//@EnableAspectJAutoProxy
@EnableTransactionManagement
@Configuration // 一定要加，不然事务不生效,因为JdbcTemplate和DataSourceTransactionManager是两个不同的datasource了
public class AppConfig { // AppConfig代理对象

//    @Bean // @Bean会覆盖@Component
//    public OrderService orderService(){
//        return new OrderService();
//    }

//    @Bean
//    public OrderService orderService2(){
//        return new OrderService();
//    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/tuling");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        return dataSource;
    }


//	@Bean
//	public SqlSessionFactory sqlSessionFactory() throws Exception {
//		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
//		sessionFactoryBean.setDataSource(dataSource());
//		return sessionFactoryBean.getObject();
//	}
//
//	@Bean
//	public SqlSessionTemplate sqlSession() throws Exception {
//		return new SqlSessionTemplate(sqlSessionFactory());
//	}
}
