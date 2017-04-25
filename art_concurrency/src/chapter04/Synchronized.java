package chapter04;

public class Synchronized {
    public static void main(String[] args) {
        // 对 Synchronized Class 对象加锁
        synchronized (Synchronized.class) {

        }

        // 静态同步方法，对 Synchronized Class 对象进行加锁
        m();
    }

    public static synchronized void m() {

    }
}
