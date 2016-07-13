/**
 * Created by Игорь on 05.07.2016.
 */
public class Deadlock2 {

    public static void main(String[] args) {

        final Object ref1 = new Object ();
        final Object ref2 = new Object ();


        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (ref1) {

                    System.out.println("T1 lock ref1");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (ref2) {

                        System.out.println("T1 lock ref2");

                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (ref2) {

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("T2 lock ref2");

                    synchronized (ref1) {

                        System.out.println("T2 lock ref1");

                    }
                }

            }
        }).start();


    }



}
