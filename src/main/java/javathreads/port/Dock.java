package javathreads.port;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Dock extends Thread {
    private final int id;
    private final Queue<Ship> shipQueue;
    private int containers;
    private final int amountofShips;
    private final Lock lock;
    private final Condition condition;

    public Dock(int id, int amountofShips, int containers) {
        this.id = id;
        this.amountofShips = amountofShips;
        this.containers = containers;
        shipQueue = new ArrayDeque<>(1);
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }

    public void addShip(Ship ship) {
        lock.lock();
        try {
            while (shipQueue.size() == 1) {
                System.out.println("Ship " + ship.getIdShip() +
                        " is waiting, dock " + id + " is full");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            shipQueue.offer(ship);
            System.out.println("Ship " + ship.getIdShip() + " is added into Dock "
                    + this.id);
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }

    public void operateShip() {

        lock.lock();
        try {
            while (shipQueue.size() == 0) {
                System.out.println("Dock " + this.id + " is free, waiting...");
                condition.await();
            }

            Ship ship = shipQueue.poll();

            System.out.println("Ship " + ship.getIdShip() + " is being operated in "
                    + "Dock " + this.id);

            if (ship.getCargo() < 30 && containers > 10) {
                ship.setCargo(ship.getCargo() + 10);
                containers -= 10;
            } else if (ship.getCargo() > 30) {
                containers += ship.getCargo();
                ship.setCargo(0);
            }


            TimeUnit.MILLISECONDS.sleep(1500);

            System.out.println("Ship " + ship.getIdShip() + " has left from Dock " + this.id);
            condition.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }


    @Override
    public void run() {
        for (int i = 0; i < amountofShips; i++) {
            operateShip();
        }
    }
}
