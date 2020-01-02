**Spring事物管理总共有四种方式**
https://www.jb51.net/article/140158.htm

代码- TransforService

测试代码- TransforTest

    编程式：
    1.注入 TransactionTemplate
    2.将利用事物的逻辑封装到 transactionTemplate#execute方法内
---
    代理BeanFactory：
    1.利用 TransactionProxyFactoryBean 为事物相关类生成代理
    2.使用方通过FactoryBean获取代理类，作为使用的Bean
---
    xml配置：
    1.利用 tx标签 + aop方式来实现
    2.<tx:advice> 标签定义事物通知，内部可有较多的配置信息
    3.<aop:config> 配置切点，切面
---
    注解方式：
    1.在开启事物的方法or类上添加 @Transactional 注解即可
    2.开启事物注解 <tx:annotation-driven transaction-manager="transactionManager"/>
    

