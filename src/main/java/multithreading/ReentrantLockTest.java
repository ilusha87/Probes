package multithreading;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Illia Krysenko on 08.06.2017.
 */
public class ReentrantLockTest {
    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        while (lock.getHoldCount() < 5) {
            lock.lock();
            System.out.println("ASD");
        }
        try {
            System.out.println("try");
        } finally {
            lock.unlock();
        }
        System.out.println(lock.getHoldCount());
    }
}
