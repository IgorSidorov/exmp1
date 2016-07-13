package ProducerConcumer;

/**
 * Created by Игорь on 09.07.2016.
 */
public class SingleElementBuffer {

    public Integer elem = null;

    public synchronized void put (int newElem) throws InterruptedException {

        while (this.elem!=null) {
            this.wait();
        }
        this.elem = newElem;
        this.notifyAll();

    }

    public synchronized int get () throws InterruptedException {

        while (elem == null) {
            this.wait();
        }
        Integer result = this.elem;
        elem = null;
        this.notifyAll();

        return result;


    }

}
