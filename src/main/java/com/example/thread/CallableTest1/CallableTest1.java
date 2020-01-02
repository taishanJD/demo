package com.example.thread.CallableTest1;

import com.google.common.base.Stopwatch;

import java.util.concurrent.*;

public class CallableTest1 {

    public static void main(String[] a){
        Stopwatch stopwatch = Stopwatch.createStarted();
        asyncTest(stopwatch);
        System.out.println("异步任务执行完毕,总耗时=="+stopwatch.elapsed(TimeUnit.MILLISECONDS));
        Stopwatch stopwatch1 = Stopwatch.createStarted();
        syncTest(stopwatch1);
        System.out.println("tong步任务执行完毕,总耗时=="+stopwatch1.elapsed(TimeUnit.MILLISECONDS));
    }


    //异步执行
    public static void asyncTest(Stopwatch stopwatch){
        ExecutorService executor = Executors.newCachedThreadPool();
        CallableTask1 callableTask1 = new CallableTask1(stopwatch,"task1",1,500);
        CallableTask1 callableTask2 = new CallableTask1(stopwatch,"task2",500,1000);

        Future<Integer> future1 = executor.submit(callableTask1);
        Future<Integer> future2 = executor.submit(callableTask2);

        try {
            System.out.println(future1.get()+"--"+future2.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    //同步执行
    public static void syncTest(Stopwatch stopwatch){
        CallableTask1 callableTask1 = new CallableTask1(stopwatch,"task1",1,500);
        CallableTask1 callableTask2 = new CallableTask1(stopwatch,"task2",500,1000);
        Integer result1 = 0;
        Integer result2 = 0;
        try {
            result1 = callableTask1.call();
            result2 = callableTask2.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result1+"--"+result2);
    }

}
