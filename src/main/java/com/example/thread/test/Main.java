package com.example.thread.test;

import java.util.LinkedList;

/*单线程的消息队列示例*/
public class Main {

    /**
     * @param args
     */

    private static Thread thread;
    private static LinkedList<Runnable> list = new LinkedList<Runnable>();

    static int test = 0;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        final long time = System.currentTimeMillis();
        for (int i = 0; i < 20; i++) {

            tastEvent(new Runnable() {
                public void run() {


                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    System.out
                            .println("第"
                                    + (++test)
                                    + ("个任务  耗时:" + (System
                                    .currentTimeMillis() - time)));
                }

            });
        }
    }

    public static void tastEvent(Runnable r) {
        synchronized (list) {
            list.add(r);
        }
        if (thread == null) {
            thread = new Thread(run);
            thread.start();
        }


    }

    static Runnable run = new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            synchronized (list) {

                while (!list.isEmpty()) {
                    // new Thread(list.poll()).start();
                    list.poll().run();
                }
                thread = null;
            }
        }
    };

}
