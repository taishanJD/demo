package com.example.thread.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestMain {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(new TestThread(new ResponseCallBack() {
            @Override
            public void printMsg(String msg) {
                System.out.println("print message: " + msg);
            }
        }));
        executorService.shutdown();
    }
}

class TestThread implements Runnable {

    private ResponseCallBack responseCallBack;

    public TestThread(ResponseCallBack responseCallBack) {
        this.responseCallBack = responseCallBack;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " start execute");
            Thread.sleep(3000);   // 耗时任务
            System.out.println(Thread.currentThread().getName() + " end execute");
            this.responseCallBack.printMsg("Hello Call Back");  // 耗时任务执行完后，通知主线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}