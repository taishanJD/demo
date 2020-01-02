package com.example.spring.mvc.service;


import com.example.spring.mvc.dao.MoneyMapper;
import com.example.spring.mvc.dao.MoneyMapper2;
import com.example.spring.mvc.model.Money;
import com.example.spring.mvc.model.MoneyExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;


@Service(value = "transforService")
public class TransforServiceImpl implements TransforService {


    @Autowired
    MoneyMapper moneyMapper;

    @Autowired
    MoneyMapper2 moneyMapper2;

    @Autowired
    TransactionTemplate transactionTemplate;


    /**
     * 转账实现1
     * 编程式事物管理，既通过TransactionTemplate来实现多个db操作的事物管理
     *
     * @param outUserId
     * @param inUserID
     * @param money
     * @param status    0 表示正常转账， 1 表示内部抛出一个异常， 2 表示新开一个线程，修改inUserId的钱 +200， 3 表示新开一个线程，修改outUserId的钱 + 200
     */
    @Override
    public void transforByTransactionTemplate(long outUserId, long inUserID, int money, int status) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                transfor(outUserId, inUserID, money, status);
            }
        });
    }


    /**
     * 转账
     *
     * @param outUserId
     * @param inUserID
     * @param money
     * @param status    0 表示正常转账， 1 表示内部抛出一个异常， 2 表示新开一个线程，修改inUserId的钱 +200， 3 表示新开一个线程，修改outUserId的钱 + 200
     */
    @Override
    @Transactional()
    public void transforByAnnotation(long outUserId, long inUserID, int money, int status) {
        transfor(outUserId, inUserID, money, status);
    }

    private void transfor(long outUserId, long inUserID, int money, int status){
        Money out = moneyMapper.selectByPrimaryKey(outUserId);
        if (out.getMoney() > money) {
            moneyMapper2.increseMoney(outUserId, -money);
            testCase(inUserID, outUserId, status);
            moneyMapper2.increseMoney(inUserID, money);
            System.out.println("转账完成! now: " + System.currentTimeMillis());
        }
    }


    // 下面都是测试用例相关
    private void testCase(final long inUserId, final long outUserId, final int status) {
        if (status == 1) {
            throw new IllegalArgumentException("转账异常!!!");
        } else if (status == 2) {
            addMoney(inUserId);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (status == 3) {
            addMoney(outUserId);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void addMoney(final long userId) {
        System.out.println("id: "+userId+" 内部加钱: " + System.currentTimeMillis());
        new Thread(new Runnable() {
            public void run() {
                moneyMapper2.increseMoney(userId, 200);
                System.out.println(" sub modify success! now: " + System.currentTimeMillis());
            }
        }).start();
    }
}
