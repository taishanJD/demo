package com.example.spring.mvc.dao;

import com.example.spring.mvc.model.Money;
import com.example.spring.mvc.model.MoneyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MoneyMapper {
    long countByExample(MoneyExample example);

    int deleteByExample(MoneyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Money record);

    int insertSelective(Money record);

    List<Money> selectByExample(MoneyExample example);

    Money selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Money record, @Param("example") MoneyExample example);

    int updateByExample(@Param("record") Money record, @Param("example") MoneyExample example);

    int updateByPrimaryKeySelective(Money record);

    int updateByPrimaryKey(Money record);

    Money selectForUpdate(Long id);
}