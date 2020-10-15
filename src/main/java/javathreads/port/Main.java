package javathreads.port;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        int amountOfShips = 5;
        Dock dock = new Dock(1, amountOfShips, 50);
        Dock dock2 = new Dock(2, amountOfShips, 50);
        Port port = new Port(dock, dock2);
        port.start();

        ExecutorService executorService = Executors.newFixedThreadPool(amountOfShips);
        for (int i = 0; i < amountOfShips; i++) {
            executorService.execute(new Ship(i + 1, 10*i, dock));
        }

        for (int i = 0; i < amountOfShips; i++) {
            executorService.execute(new Ship(i + 6, 10*i, dock2));
        }

        executorService.shutdown();


    }
}
