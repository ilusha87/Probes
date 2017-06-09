package multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Illia Krysenko on 08.06.2017.
 */
public class CycleExecutorPoolTest {
    private static volatile ExecutorService service = Executors.newFixedThreadPool(8);
    ;

    public static void main(String[] args) {
        LazyChkAccessBundleMapMaket lazyChkAccessBundleMapMaket = new LazyChkAccessBundleMapMaket();
        lazyChkAccessBundleMapMaket.initialize();
    }


    private static class LazyChkAccessBundleMapMaket {
        private ReentrantLock lock = new ReentrantLock(); //  if you make lock as static here; then output will be
        /*LOCK TIMEOUT!!
LOCK TIMEOUT!!
LOCK TIMEOUT!!
LOCK TIMEOUT!!
LOCK TIMEOUT!!
LOCK TIMEOUT!!
LOCK TIMEOUT!!
LOCK TIMEOUT!!
pool-1-thread-1 0
pool-1-thread-2 1
pool-1-thread-3 2
pool-1-thread-4 3
pool-1-thread-5 4
pool-1-thread-6 5
pool-1-thread-7 6
pool-1-thread-8 7*/

        void initialize() {
            try {
                if (lock.tryLock(10, TimeUnit.SECONDS)) { //lock here is separate per each class instance and generally has no influence for the STUCK
                    try {
                        internalInit();
                    } finally {
                        lock.unlock();
                        service.shutdown();
                    }
                } else {
                    System.out.println("LOCK TIMEOUT!!");
                }
            } catch (InterruptedException e) {
                throw new IllegalStateException("Initialization interrupted!", e);
            }

        }

        void internalInit() {
            List<SimpleThread> checkers = new ArrayList<SimpleThread>(8);
            for (int i = 0; i < 8; i++) {
                checkers.add(new SimpleThread(i));
            }
            List<Future<String>> futures;
            try {
                futures = service.invokeAll(checkers);
                for (Future<String> future : futures) {
                    String list = future.get();
                    System.out.println(list);
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

    }

    private static class SimpleThread implements Callable<String> {

        private int number;

        SimpleThread(int number) {
            this.number = number;
        }

        @Override
        public String call() throws Exception {
            LazyChkAccessBundleMapMaket lazyChkAccessBundleMapMaket = new LazyChkAccessBundleMapMaket();
            lazyChkAccessBundleMapMaket.initialize();
            return Thread.currentThread().getName() + " " + number;
        }


    }
}
