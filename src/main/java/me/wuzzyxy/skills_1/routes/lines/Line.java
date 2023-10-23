package me.wuzzyxy.skills_1.routes.lines;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Line implements Cloneable{
    private String name;
    private String type;
    private int id;

    @JsonProperty("stops") // This annotation is used to map the JSON field "stops" to the Java field "stops"
    private List<Stop> stops;

    public Line() {
        stops = new ArrayList<Stop>();
    }

    public boolean hasStopID(int id) {
        for (Stop stop : stops) {
            if (stop.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean addStop(Stop stop) {
        if (hasStopID(stop.getId())) {
            return false;
        }
        return stops.add(stop);
    }


    // Getter and Setter methods
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public List<Stop> getStops() {
        return stops;
    }

    @Override
    public Object clone()  {
        try {
            Line line = (Line) super.clone();
            line.stops = new ArrayList<>();
            return line;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public String toString() {
        return name + " " + type + " id:" + id + " " + stops.size() + " stops";
    }
}
