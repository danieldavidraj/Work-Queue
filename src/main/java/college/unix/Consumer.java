package college.unix;

public class Consumer implements Runnable {
    Main m;
    int consumerCount;
    LimitedQueue q;

    public Consumer(LimitedQueue q, Main m, int cc) {
        this.q = q;
        this.m = m;
        this.consumerCount = cc;
    }

    @Override
    public void run() {
        while (true) {
            q.takeJob(consumerCount);
        }
    }
}
