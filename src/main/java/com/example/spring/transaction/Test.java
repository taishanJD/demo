package com.example.spring.transaction;

import com.example.spring.mvc.service.TransforService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Repository
public class Test {

    @Autowired
    TransactionTemplate transactionTemplate;


    public void transfor(final int inUserId, final int outUserId, final int payMoney, final int status) {

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {

            }
        });

    }
    }
