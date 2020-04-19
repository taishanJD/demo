package com.example.spring.core;

import com.example.spring.mvc.model.Money;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    public Config(){
        System.out.println("config 初始化。。");
    }

    @Bean(name = "money")
    public Money getMoney(){
        Money money = new Money();
        money.setName("jdjd");
        return money;
    }
}
