package me.wuzzyxy.skills_1.routes.lines;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Lines{
    @JsonProperty("lines")
    private List<Line> lines;

    public Lines() {
        lines = new ArrayList<Line>();
    }

    // Getter and Setter methods
    public List<Line> getLines() {
        return lines;
    }

    public Stop getStopByID(int id) {
        for (Line line : lines) {
            for (Stop stop : line.getStops()) {
                if (stop.getId() == id) {
                    return stop;
                }
            }
        }
        return null;
    }

    public void addLine(Line line) {
        lines.add(line);
    }

    public String toString() {
        return lines.toString();
    }
}
