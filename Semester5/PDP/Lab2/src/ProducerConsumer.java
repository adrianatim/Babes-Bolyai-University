import java.util.*;

public class ProducerConsumer {

    private static final int CAPACITY = 2;
    private static final int MILLIS = 1000;
    private static int NR_RANGE = 10;
    private final List<Integer> vectorsI = new LinkedList<>();
    private final List<Integer> vectorsJ = new LinkedList<>();
    private final Queue<Integer> proxyQueue = new PriorityQueue<>();
    private int sum = 0;

    public ProducerConsumer() {
        this.generateVectors();
    }

    public void produce() throws InterruptedException {
        int vectorIndex = 1;
        while (true) {
            synchronized (this) {
                while (proxyQueue.size() == CAPACITY || NR_RANGE < 1)
                    wait();

                int vector1 = vectorsI.get(vectorIndex - 1);
                int vector2 = vectorsJ.get(vectorIndex - 1);
                int multiply = vector1 * vector2;
                System.out.println("Producer" + vectorIndex + " : "
                        + vector1 + " * " + vector2 + " = " + multiply);
                vectorIndex++;
                NR_RANGE--;
                this.proxyQueue.add(multiply);

                notify();

                Thread.sleep(MILLIS);
            }
        }
    }

    // Function called by consumer thread
    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (proxyQueue.size() < 1)
                    wait();

                sum = sum + proxyQueue.poll();

                System.out.println("Consumer consumes: sum= " + sum);

                notify();

                Thread.sleep(MILLIS);
            }
        }
    }

    private void generateVectors() {
        Random rand = new Random();

        for (int i = 0; i < NR_RANGE; i++) {
            int valueI = rand.nextInt(NR_RANGE) - NR_RANGE / 2;
            int valueJ = rand.nextInt(NR_RANGE) - NR_RANGE / 2;
            vectorsI.add(valueI);
            vectorsJ.add(valueJ);
        }
    }
}
