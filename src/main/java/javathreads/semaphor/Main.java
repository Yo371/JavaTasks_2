package javathreads.semaphor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore semaphorePark = new Semaphore(3);
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 6; i++) {
            executorService.execute(new CarThread(i, semaphorePark));
        }
        executorService.shutdown();
    }
}
