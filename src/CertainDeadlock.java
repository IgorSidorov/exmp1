/**
 * Created by Игорь on 08.07.2016.
 */

    import java.util.concurrent.CountDownLatch;
    import java.util.concurrent.locks.Lock;
    import java.util.concurrent.locks.ReentrantLock;

    public class CertainDeadlock {
        static class Locker implements Runnable {
            private CountDownLatch latch;
            private Lock first, second;

            Locker(CountDownLatch latch, Lock first, Lock second) {
                this.latch = latch;
                this.first = first;
                this.second = second;
            }

            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                try {
                    first.lock();
                    latch.countDown();
                    System.out.println(threadName + ": locked first lock");
                    latch.await();
                    System.out.println(threadName + ": attempting to lock second lock");
                    second.lock();
                    System.out.println(threadName + ": never reached");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        public static void main(final String... args) {
            CountDownLatch latch = new CountDownLatch(2);
            Lock lock1 = new ReentrantLock(), lock2 = new ReentrantLock();
            new Thread(new Locker(latch, lock1, lock2), "Thread 1").start();
            new Thread(new Locker(latch, lock2, lock1), "Thread 2").start();
        }
    }

