package ProducerConcumer;

/**
 * Created by Игорь on 09.07.2016.
 */
public class Producer implements Runnable {

    private int startValue;
    private final int period;
    private final SingleElementBuffer buffer;

    public Producer (int startValue, int period, SingleElementBuffer buffer) {
        this.startValue = startValue;
        this.period = period;
        this.buffer = buffer;
    }


    @Override
    public void run() {
        while (true) {
            try {
                System.out.printf(startValue + " producer");
                buffer.put(startValue++);
                Thread.sleep(period);
            } catch (InterruptedException e) {
                System.out.printf(Thread.currentThread().getName() + "stopped.");
                break;
            }

        }

    }
}
