package javathreads.port;

import java.util.Objects;

public class Ship extends Thread {
    private final int id;
    private int cargo;
    private final Dock dock;

    public Ship(int id, int cargo, Dock dock) {
        this.id = id;
        this.cargo = cargo;
        this.dock = dock;
        //this.start();
    }

    public int getCargo() {
        return cargo;
    }

    public void setCargo(int cargo) {
        this.cargo = cargo;
    }

    public int getIdShip() {
        return id;
    }

    @Override
    public void run() {
        this.dock.addShip(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship ship = (Ship) o;
        return id == ship.id &&
                cargo == ship.cargo &&
                Objects.equals(dock, ship.dock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cargo, dock);
    }

    @Override
    public String toString() {
        return "Ship{" +
                "id=" + id +
                ", cargo=" + cargo +
                ", dock=" + dock +
                '}';
    }
}
