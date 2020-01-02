package com.example.spring.mvc.service;

public interface TransforService {

    //通过硬编码方式实现事务
    void transforByTransactionTemplate(long outUserId, long inUserID, int money, int status);

    //通过注解方式实现事务
    void transforByAnnotation(long outUserId, long inUserID, int money, int status);
}
