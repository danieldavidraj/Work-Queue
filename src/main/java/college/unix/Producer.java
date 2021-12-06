package college.unix;

public class Producer implements Runnable {
    Main m;
    int producerCount;
    LimitedQueue q;

    public Producer(LimitedQueue q, Main m, int pc) {
        this.q = q;
        this.m = m;
        this.producerCount = pc;
        new Thread(this, "Producer").start();
    }

    @Override
    public void run() {
        try {
            q.putJob();
        } finally {
            m.producerExit(producerCount);
        }
    }
}
