package me.wuzzyxy.skills_1.routes.lines;

public class Stop implements Cloneable{
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

    @Override
    protected Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
