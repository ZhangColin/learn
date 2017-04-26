package chapter05;

import chapter04.SleepUtils;

import java.util.concurrent.locks.Lock;

public class TwinsLockTest {
    public static void main(String[] args) {
        TwinsLockTest test = new TwinsLockTest();
        test.test();
    }

    public void test() {
        final Lock lock = new TwinsLock();
        class Worker extends Thread {
            public void run() {
                while (true) {
                    lock.lock();
                    try {
                        SleepUtils.second(1);
                        System.out.println(Thread.currentThread().getName());
                        SleepUtils.second(1);
                    }
                    finally {
                        lock.unlock();
                    }
                }
            }
        }

        // 启动 10 个线程
        for (int i = 0; i < 10; i++) {
            Worker w = new Worker();
            w.setDaemon(true);
            w.start();
        }

        // 每隔一秒换行
        for (int i = 0; i < 10; i++) {
            SleepUtils.second(1);
            System.out.println();
        }
    }
}
