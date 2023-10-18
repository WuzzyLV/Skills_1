package me.wuzzyxy.skills_1.routes.lines;

public class Stop {
    private String name;
    private int id;

    // Getter and Setter methods
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return name + " " + id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Stop) {
            Stop stop = (Stop) obj;
            return stop.getId() == id;
        }
        return false;
    }
}
