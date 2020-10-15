package javathreads;

import java.util.concurrent.*;

public class Plane extends Thread{
    private Phaser phaser;
    private final int id;
    private Semaphore semaphore;
    private CyclicBarrier cyclicBarrier;

    public Plane(Phaser phaser, int id, CyclicBarrier cyclicBarrier) {
        this.phaser = phaser;
        this.id = id;
        this.start();
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try{

            cyclicBarrier.await();
            TimeUnit.MILLISECONDS.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " phase one");

            cyclicBarrier.await();
            TimeUnit.MILLISECONDS.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " phase two");

            cyclicBarrier.await();
            TimeUnit.MILLISECONDS.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " phase three");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Phaser phaser = new Phaser(1);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        for (int i = 0; i < 6; i++) {
            new Plane(phaser, i, cyclicBarrier);
        }
    }
}
