package com.example.thread.CallableTest1;

import com.google.common.base.Stopwatch;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

class CallableTask1 implements Callable<Integer> {

    private String name;
    private Integer begin;
    private Integer end;
    private Stopwatch stopwatch;

    public CallableTask1(Stopwatch stopWatch,String name, Integer begin, Integer end) {
        this.name = name;
        this.begin = begin;
        this.end = end;
        this.stopwatch = stopWatch;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Integer call() throws Exception {

        Integer sum = 0;
        for (int i = begin;i<end;i++){
            sum+=i;
            Thread.sleep(5);
        }
        System.out.println(this.name+",耗时(ms)=="+stopwatch.elapsed(TimeUnit.MILLISECONDS));
        return sum;
    }
}
