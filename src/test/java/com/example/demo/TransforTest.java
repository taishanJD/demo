package com.example.demo;

import com.example.spring.mvc.dao.MoneyMapper;
import com.example.spring.mvc.service.TransforService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = DemoApplication.class)
//@WebAppConfiguration
public class TransforTest extends BaseTest {

    @Autowired
    TransforService transforService;

    @Autowired
    MoneyMapper moneyMapper;

    private void testTransforByTransactionTemplate(int status) {

        System.out.println("---------status == -"+status+"-----begin----");
        System.out.println("---------before----------");
        System.out.println("id: 1 money = " + moneyMapper.selectForUpdate(1l).getMoney());
        System.out.println("id: 2 money = " + moneyMapper.selectForUpdate(2l).getMoney());


        transforService.transforByTransactionTemplate(1, 2, 100, status);

        System.out.println("---------after----------");
        System.out.println("id: 1 money = " + moneyMapper.selectForUpdate(1l).getMoney());
        System.out.println("id: 2 money = " + moneyMapper.selectForUpdate(2l).getMoney());
        System.out.println("---------status == -"+status+"-----end----");
        System.out.println();
    }

    @Test
    public void testTransforByTransactionTemplate() {

        testTransforByTransactionTemplate(2);
        testTransforByTransactionTemplate(3);
        testTransforByTransactionTemplate(1);
    }

}
