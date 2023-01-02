package com.zhouyu.service;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * @author 周瑜
 */
@Component
public class UserService implements InitializingBean {

//    @Autowired
//    private OrderService orderService;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserServiceBase userServiceBase;
    @Transactional
    public void test(){
        jdbcTemplate.execute("insert into T1 values (1,1,1,1,1)");
        a();// 事务失效，普通对象在执行a()
        userServiceBase.a(); // 事务不失效，userServiceBase的代理对象在执行a()
    }

    // 事务失效
    @Transactional(propagation = Propagation.NEVER)
    public void a(){

    }
//    @Autowired
//    private User admin;

    //    public UserService(){
//        System.out.println("1");
//    }
//    @Autowired //加了@Autowired 会直接用该构造方法
//    public UserService(OrderService orderService123) { // Map<beanName,bean对象>
//        this.orderService = orderService123;
//        System.out.println("2");
//    }

    //    public UserService(OrderService orderService,OrderService orderService1){
//        this.orderService = orderService;
//        System.out.println("3");
//    }
//    @PostConstruct
//    public void a(){
//        // mysql--->User对象--->admin
//    }


    @Override
    public void afterPropertiesSet() throws Exception {
        // mysql--->User对象--->admin
    }
}
