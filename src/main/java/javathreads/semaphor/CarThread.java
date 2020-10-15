package javathreads.semaphor;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class CarThread extends Thread {
    private final int id;
    private final Semaphore semaphore;

    public CarThread(int id, Semaphore semaphore) {
        this.id = id;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        System.out.println(id + " trying to enter");
        try {
            if (semaphore.tryAcquire(100, TimeUnit.MILLISECONDS)) {
                semaphore.acquire();
                System.out.println(id + " parking");
                TimeUnit.MILLISECONDS.sleep(2000);
                System.out.println(id + " end parking");
            } else {
                System.out.println(id + " has left");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}
