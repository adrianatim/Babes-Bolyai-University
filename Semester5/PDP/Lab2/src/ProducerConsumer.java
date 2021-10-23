import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumer {

    // Size of the capacity of this 2 queue is 2.
    //queue vectorI - stores only the i- values of the vector
    //queue vectorJ - stores only the j- values of the vector
    private final BlockingQueue<Integer> vectorI = new LinkedBlockingQueue<>();
    private final BlockingQueue<Integer> vectorJ = new LinkedBlockingQueue<>();
    private static final int CAPACITY = 2;
    private static final int MILLIS = 1000;
    private static final int NR_RANGE = 20;
    Random rand = new Random();

    // Function called by producer thread
    public void produce() throws InterruptedException {
        int vectorIndex = 1;
        while (true) {
            synchronized (this) {
                //generate random values (+/-) for the i and j values
                int valueI = rand.nextInt(NR_RANGE) - NR_RANGE / 2;
                int valueJ = rand.nextInt(NR_RANGE) - NR_RANGE / 2;

                // producer thread waits while list
                // is full
                while (vectorI.size() == CAPACITY || vectorJ.size() == CAPACITY)
                    wait();

                System.out.println("Producer: v" + vectorIndex + " = "
                        + valueI + "i + " + valueJ + "j");

                //increased index to keep track with the generated vectors
                vectorIndex++;

                // produce the values of 1 vector and then add it to the blockingQueue
                vectorI.add(valueI);
                vectorJ.add(valueJ);

                // notifies the consumer thread that
                // now it can start consuming
                notify();

                Thread.sleep(MILLIS);
            }
        }
    }

    // Function called by consumer thread
    public void consume() throws InterruptedException {
        while (true) {
            int sum;
            synchronized (this) {
                // consumer thread waits while the queues has at most 1 element
                while (vectorI.size() <= 1 || vectorJ.size() <= 1)
                    wait();

                // in order to make the sum of 2 vectors, we need to extract the first 2 values added in the blockingQueues
                int firstVectorI = vectorI.poll();
                int firstVectorJ = vectorJ.poll();
                sum = firstVectorI * vectorI.poll() + firstVectorJ * vectorJ.poll();

                System.out.println("Consumer consumed: sum= "
                        + sum);

                // Wake up producer thread
                notify();

                Thread.sleep(MILLIS);
            }
        }
    }

}
