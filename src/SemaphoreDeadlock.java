import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * Created by Игорь on 06.07.2016.
 */
public class SemaphoreDeadlock {
    static class Locker implements Runnable {
        private CountDownLatch latch;
        private Semaphore semaphore;

        Locker(CountDownLatch latch, Semaphore semaphore) {
            this.latch = latch;
            this.semaphore = semaphore;

        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            try {
                semaphore.acquire();
                System.out.println(threadName + ": locked first lock");
                latch.countDown();

                latch.await();
                System.out.println(threadName + ": attempting to lock second lock");
                semaphore.acquire();
                System.out.println(threadName + ": never reached");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(final String... args) {
        CountDownLatch latch = new CountDownLatch(2);
        Semaphore semaphore = new Semaphore(2);
        new Thread(new Locker(latch, semaphore), "Thread 1").start();
        new Thread(new Locker(latch, semaphore), "Thread 2").start();
    }

}
