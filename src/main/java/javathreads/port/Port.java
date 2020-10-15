package javathreads.port;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Port extends Thread {
    private ArrayList<Dock> dockArrayList;

    public Port(Dock... docks) {
        this.dockArrayList = new ArrayList<>();
        this.dockArrayList.addAll(Arrays.asList(docks));
    }

    @Override
    public void run() {
        dockArrayList.forEach(Thread::start);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Port port = (Port) o;
        return Objects.equals(dockArrayList, port.dockArrayList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dockArrayList);
    }

    @Override
    public String toString() {
        return "Port{" +
                "dockArrayList=" + dockArrayList +
                '}';
    }
}
