package college.unix;

@SuppressWarnings("deprecation")
public class Main implements Runnable {
    int queueCapacity;
    int producerThreadsCount;
    int consumerThreadsCount;
    Thread[] consumerThreads;

    public Main(int qc, int pt, int ct) {
        this.queueCapacity = qc;
        this.producerThreadsCount = pt;
        this.consumerThreadsCount = ct;
        this.consumerThreads = new Thread[ct];
        new Thread(this, "Main").start();
    }

    public void producerExit(int producerCount) {
        System.out.println("Producer " + producerCount + " Finished");
    }

    public void stopConsumerThreads() {
        for(int i = 0; i < consumerThreadsCount; i++) {
            this.consumerThreads[i].stop();
        }
    }
    
    @Override
    public void run() {
        LimitedQueue queue = new LimitedQueue(queueCapacity);
        for(int i = 0; i < producerThreadsCount; i++) {
            new Producer(queue, this, i + 1);
        }
        for(int i = 0; i < consumerThreadsCount; i++) {
            Consumer c = new Consumer(queue, this, i + 1);
            consumerThreads[i] = new Thread(c);
            consumerThreads[i].start();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        new awaitTermination(queue, this);
    }
}
