package me.wuzzyxy.skills_1.routes.lines;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Line {
    private String name;
    private String type;
    private int id;

    @JsonProperty("stops") // This annotation is used to map the JSON field "stops" to the Java field "stops"
    private List<Stop> stops;

    public boolean hasStopID(int id) {
        for (Stop stop : stops) {
            if (stop.getId() == id) {
                return true;
            }
        }
        return false;
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

    public String toString() {
        return name + " " + type + " id:" + id + " " + stops.size() + " stops";
    }
}
