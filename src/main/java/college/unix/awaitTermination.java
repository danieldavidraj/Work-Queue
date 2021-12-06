package college.unix;

public class awaitTermination implements Runnable {
    LimitedQueue q;
    Main m;

    public awaitTermination(LimitedQueue q, Main m) {
        this.q = q;
        this.m = m;
        new Thread(this, "awaitTermination").start();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(true) {
            if(q.jobsCompleted()) {
                m.stopConsumerThreads();
                break;
            }
        }
    }
}
