package com.example.thread.CDLorCB;

import java.util.Random;
import java.util.concurrent.CountDownLatch;


/**
 * 对于CountDownLatch，其他线程为游戏玩家，比如英雄联盟，主线程为控制游戏开始的线程。
 * 在所有的玩家都准备好之前，主线程是处于等待状态的，也就是游戏不能开始。
 * 当所有的玩家准备好之后，下一步的动作实施者为主线程，即开始游戏。
 *
 * CountDownLatch和CyclicBarrier都有让多个线程等待同步然后再开始下一步动作的意思，
 * 但是CountDownLatch的下一步的动作实施者是主线程，具有不可重复性；
 * 而CyclicBarrier的下一步动作实施者还是“其他线程”本身，具有往复多次实施动作的特点。
 */
public class CountDownLatchTest {

    public static void main(String[] a) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0;i<countDownLatch.getCount();i++){
            new Thread(new Player("player--"+i,countDownLatch)).start();
        }
        System.out.println("等待进入游戏。。。");
        countDownLatch.await();
        System.out.println("开始游戏。。。");
    }

    static class Player implements Runnable{

        private String playerName;
        private CountDownLatch countDownLatch;

        public Player(String playerName,CountDownLatch countDownLatch) {
            this.playerName = playerName;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            Random random = new Random();
            int radomNum = random.nextInt(5000)+1000;
            try {
                Thread.sleep(radomNum);
                System.out.println(playerName+"进入游戏，用时=="+radomNum);
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
