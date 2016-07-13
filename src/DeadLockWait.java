/**
 * Created by Игорь on 10.07.2016.
 */
public class DeadLockWait {


        public static Object Lock1 = new Object();
        public static Object Lock2 = new Object();

        public static void main(String args[]) {

            ThreadDemo1 T1 = new ThreadDemo1();
            ThreadDemo2 T2 = new ThreadDemo2();
            T1.start();
            T2.start();

        }

        private static class ThreadDemo1 extends Thread {
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (Lock1) {
                    System.out.println("Thread 1: Holding lock 1...");
                    Lock1.notifyAll();

                    synchronized (Lock2) {
                        try {     Lock2.wait();
                        } catch ( InterruptedException e) {}
                        System.out.println("Thread 1: Holding lock 1 & 2...");
                    }
                }
            }
        }
        private static class ThreadDemo2 extends Thread {
            public void run() {
                synchronized (Lock2) {
                    System.out.println("Thread 2: Holding lock 2...");
                    Lock2.notifyAll();

                    synchronized (Lock1) {
                        try {     Lock1.wait();
                        } catch ( InterruptedException e) {}
                        System.out.println("Thread 2: Holding lock 1 & 2...");
                    }
                }
            }
        }
    }

