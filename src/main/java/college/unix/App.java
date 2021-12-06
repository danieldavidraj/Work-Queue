package college.unix;

public class App {
    public static void main(String[] args) {
        
        int QUEUE_CAPACITY = 15;
        int PRODUCER_THREADS = 1;
        int CONSUMER_THREADS = 1;

        if(args.length == 2) {
            PRODUCER_THREADS = Integer.parseInt(args[0]);
            CONSUMER_THREADS = Integer.parseInt(args[1]);
        }
        
        new Main(QUEUE_CAPACITY, PRODUCER_THREADS, CONSUMER_THREADS);
    }
}
