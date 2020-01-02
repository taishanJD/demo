package com.example.spring.mvc.dao;

import org.apache.ibatis.annotations.Param;

public interface MoneyMapper2 {

    void increseMoney(@Param("userId") long userId, @Param("money") int money);
}
