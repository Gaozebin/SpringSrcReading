package com.zhouyu;

import com.zhouyu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author gaozebin
 * @date 2023/1/2 15:59
 */
public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//        ClassPathXmlApplicationContext context1 = new ClassPathXmlApplicationContext("spring.xml");
        // bean 对象
        // UserService.class --> 无参构造方法-->对象-->依赖注入（属性赋值）------>bean
        // Bean的创建生命周期：
        // UserService.class --> 无参构造方法（推断构造方法）-->普通对象-->依赖注入（属性赋值）-->初始化前(@PostConstruct)-->
        // 初始化（InitializingBean）-->初始化后(AOP)-->代理对象------>Bean
        // Bean的销毁生命周期

        UserService userService = (UserService)context.getBean("userService");// orderService有值，自己new一个UserService没有值
        userService.test();

        // 找出加了Autowired注解的属性
        //        for (Field field : userService.getClass().getFields()) {
        //            if (field.isAnnotationPresent(Autowired.class)){
        //                field.set(userService,"??");
        //            }
        //        }

        //        for (Method method : userService.getClass().getMethods()) {
        //            if (method.isAnnotationPresent(PostConstruct.class)){
        //                method.invoke(userService,null);
        //            }
        //        }
        // 对象instance of InitializingBean
        // ((InitializingBean对象)) .afterPropertiesSet()
        // System.out.println(context.getBean("orderService"));
        // System.out.println(context.getBean("orderService1"));
        // System.out.println(context.getBean("orderService2"));

        // UserServiceProxy--->代理对象--->代理对象.target=普通对象
        // 代理对象.test()

        //        class UserServiceProxy extends UserService{
        //            UserService target;
        //            public void test(){
        //                // 执行@Before
        //                // 1.@Transa
        //                // 2.创建一个数据库连接conn（事务管理器dataSource）
        //                // 3.conn.auticommit = false
        //                target.test(); //sql1,sql2
        //            }
        //        }
        // 切面切的是方法，所以代理对象里面的属性(orderService)没有赋值（没地方需要用）

        // 判断对象要不要AOP-->对象方法有没有被切?
        // 1、找出所有的切面Bean
        // 2、遍历所有切面Bean里的方法（存在缓存map里）
        // 3、切面Bean的方法与对象（UserService）的方法匹配==》对象（UserService）需要AOP
    }

}
