package college.unix;

import java.util.Scanner;
import java.util.LinkedList;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.util.NoSuchElementException;

public class LimitedQueue extends LinkedList<Character> {
    private int limit;
    boolean producerFinished = false;
    int producerCount = 0;
    int executed_jobs = 0;
    int waiting_jobs = 0;

    public LimitedQueue(int limit) {
        this.limit = limit;
    }

    @Override
    public boolean add(Character o) {
        if(size() >= limit) {
            System.out.println("Size " + size() + " exceeded limit " + limit); 
            return false;
        }
        super.add(o);
        return true;
    }

    public void putJob() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       
        try {
            Scanner scanner = new Scanner(Paths.get("work.txt"), StandardCharsets.UTF_8.name());
            String work = scanner.useDelimiter("\\A").next();
            scanner.close();

            String[] splited = work.split("\\s+");

            for(String job: splited) {  
                if(job.charAt(0) == 's') {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    waiting_jobs++;
                    this.add(job.charAt(0));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void takeJob(int consumerCount) {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            char job = this.remove();
            System.out.println("Consumer " + consumerCount + " Starting Job " + job); 
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Consumer " + consumerCount + " Ending Job " + job); 
            executed_jobs++;
        }
        catch (NoSuchElementException e) {
            System.out.println("Queue Empty");
            jobsCompleted();
        }
    }

    public boolean jobsCompleted() {
        if (executed_jobs == waiting_jobs) {
            System.out.println(executed_jobs + " Jobs Done"); 
            return true;
        }
        return false;
    }
}
