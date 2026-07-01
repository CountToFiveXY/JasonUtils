package Algorithm.Company;

public class BlockingQueue {

    private int[] items;
    private int count = 0;

    private int in = 0;
    private int out = 0;
    private int size;

    public BlockingQueue(int capacity) {
        size = capacity;
        this.items = new int[capacity];
    }

    // enqueue
    public synchronized void enqueue(int val) throws InterruptedException {

        while (count == size) {
            System.out.println("queue full, producer waiting");
            wait(); // queue full
        }
        int pos = in % size;

        items[pos] = val;
        System.out.println("enqueue " + val);
        in++;
        count++;

        notifyAll(); // wake up dequeue
    }

    // dequeue
    public synchronized int dequeue() throws InterruptedException {

        while (count == 0) {
            System.out.println("queue empty, producer waiting");
            wait(); // queue empty
        }

        int pos = out % size;

        int res = items[pos];
        out++;
        count--;
        notifyAll(); // wake up enqueue

        return res;
    }

    public synchronized int size() {
        System.out.println("curr size: " + count);
        return count;
    }

    public static void main(String[] args) throws InterruptedException{
        BlockingQueue bq = new BlockingQueue(3);

        Thread producer = new Thread(() -> {
            try {
                bq.enqueue(0);
                bq.enqueue(1);
                bq.enqueue(2);
                bq.size();
                bq.enqueue(3); // will block until dequeue
                System.out.println("enqueue 3 done");
            } catch (Exception e) {}
        });

        Thread consumer = new Thread(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("deque" + bq.dequeue());
                System.out.println("deque" + bq.dequeue());

            } catch (Exception e) {}
        });

        producer.start();
        consumer.start();
    }
}
