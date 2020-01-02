package com.example.thread.CDLorCB;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *
 * 对于CyclicBarrier，假设有一家公司要全体员工进行团建活动，
 * 活动内容为翻越三个障碍物，每一个人翻越障碍物所用的时间是不一样的。
 * 但是公司要求所有人在翻越当前障碍物之后再开始翻越下一个障碍物，
 * 也就是所有人翻越第一个障碍物之后，才开始翻越第二个，以此类推。
 * 类比地，每一个员工都是一个“其他线程”。当所有人都翻越的所有的障碍物之后，程序才结束。
 * 而主线程可能早就结束了，这里我们不用管主线程。
 *
 * CountDownLatch和CyclicBarrier都有让多个线程等待同步然后再开始下一步动作的意思，
 * 但是CountDownLatch的下一步的动作实施者是主线程，具有不可重复性；
 * 而CyclicBarrier的下一步动作实施者还是“其他线程”本身，具有往复多次实施动作的特点。
 **/
public class CyclicBarrierTest {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(4);
        for (int i = 0; i < cyclicBarrier.getParties(); i++) {
            new Thread(new Player("player--"+i,cyclicBarrier)).start();
        }
        System.out.println("main thread over...");
    }

    static class Player implements Runnable {

        private String playerName;
        private CyclicBarrier cyclicBarrier;

        public Player(String playerName, CyclicBarrier cyclicBarrier) {
            this.playerName = playerName;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            //模拟三个障碍
            for (int i = 0; i < 3; i++) {
                Random random = new Random();
                int radomNum = random.nextInt(5000) + 1000;
                try {
                    Thread.sleep(radomNum);
                    System.out.println(playerName + "跨过障碍-"+i+"，用时==" + radomNum);
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
