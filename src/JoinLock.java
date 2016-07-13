/**
 * Created by Игорь on 06.07.2016.
 */
public class JoinLock {

    public static void main(String[] args) throws InterruptedException {

        Thread T1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.currentThread().join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });



        T1.start();
        T1.join();
    }




}
