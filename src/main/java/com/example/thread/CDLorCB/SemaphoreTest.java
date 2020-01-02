package com.example.thread.CDLorCB;

import java.util.Random;
import java.util.concurrent.Semaphore;


/**
 *
 * 本例中模拟5台机器和8个工人
 *
    1）CountDownLatch和CyclicBarrier都能够实现线程之间的等待，只不过它们侧重点不同：

 　　　　CountDownLatch一般用于某个线程A等待若干个其他线程执行完任务之后，它才执行；

 　　　　而CyclicBarrier一般用于一组线程互相等待至某个状态，然后这一组线程再同时执行；

 　　　　另外，CountDownLatch是不能够重用的，而CyclicBarrier是可以重用的。

 　　2）Semaphore其实和锁有点类似，它一般用于控制对某组资源的访问权限。
 */
public class SemaphoreTest {
    private static final int NUMBER = 5;    //限制资源访问数,在本例中是机器数
    private static final Semaphore avialable = new Semaphore(NUMBER, true);

    public static void main(String[] args) {
        for (int i=0;i<8;i++){//模拟8个工人去操控5台机器
            new Thread(new Worker("worker--"+i,avialable)).start();
        }
    }

    static class Worker implements Runnable {

        private String name;
        private Semaphore semaphore;

        public Worker(String name, Semaphore semaphore) {
            this.name = name;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(name+"正在使用机器,剩余数量=="+semaphore.availablePermits());
                Random random = new Random();
                int randomNum = random.nextInt(5000) + 1000;
                Thread.sleep(randomNum);
                System.out.println(name+"机器使用完毕，释放，使用时间=="+randomNum);
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
