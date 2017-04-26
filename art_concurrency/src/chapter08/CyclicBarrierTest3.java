package chapter08;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest3 {
    static CyclicBarrier c = new CyclicBarrier(2);

    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            try {
                c.await();
            } catch (InterruptedException e) {
            } catch (BrokenBarrierException e) {
            }
        });

        thread.start();
        thread.interrupt();

        try {
            c.await();
        } catch (Exception e) {
            System.out.println(c.isBroken());
        }

    }
}
