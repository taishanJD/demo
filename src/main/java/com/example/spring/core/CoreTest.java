package com.example.spring.core;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class CoreTest {


    //参考： https://segmentfault.com/a/1190000013700859

    /**
     * Core模块是：IOC容器，解决对象创建和之间的依赖关系。
     *
     * 在Spring中总体来看可以通过三种方式来配置对象:
     *
     * 使用XML文件配置
     * 使用注解来配置
     * 使用JavaConfig来配置
     *
     * 1) 对象创建： 单例/多例
     *     scope="singleton", 默认值， 即 默认是单例    【service/dao/工具类】
     *  scope="prototype", 多例；                 【Action对象】
     *
     * 2) 什么时候创建?
     *       scope="prototype"  在用到对象的时候，才创建对象。
     *    scope="singleton"  在启动(容器初始化之前)， 就已经创建了bean，且整个应用只有一个。
     * 3)是否延迟创建
     *       lazy-init="false"  默认为false,  不延迟创建，即在启动时候就创建对象
     *       lazy-init="true"   延迟初始化， 在用到对象的时候才创建对象
     *    （只对单例有效）
     * 4) 创建对象之后，初始化/销毁
     *       init-method="init_user"       【对应对象的init_user方法，在对象创建之后执行 】
     *    destroy-method="destroy_user"  【在调用容器对象的destroy方法时候执行，(容器用实现类)】
     */
    public static void main(String[] args) {
        //1-1 加载Spring的资源文件
        Resource resource = new ClassPathResource("applicationContext.xml");
        //1-2 创建IOC容器对象【IOC容器=工厂类+applicationContext.xml】
        BeanFactory beanFactory = new XmlBeanFactory(resource);
        System.out.println(resource);
        System.out.println(beanFactory);

        //2-1 得到IOC容器对象
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        ac.containsBean("money");
        System.out.println(ac);

    }
}
