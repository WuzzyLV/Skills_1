package me.wuzzyxy.skills_1.routes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.wuzzyxy.skills_1.routes.lines.Line;
import me.wuzzyxy.skills_1.routes.lines.Lines;
import me.wuzzyxy.skills_1.routes.lines.Stop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class RouteSystem {

    Logger logger = LoggerFactory.getLogger(RouteSystem.class);

    private Lines lines;
    private Map<Integer, List<Line>> graph;
    private List<Stop> allStops;

    public RouteSystem() {
        try {
            lines = new ObjectMapper().readValue(
                    new ClassPathResource("json/routes_small.json").getInputStream(),
                    Lines.class
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        graph = new HashMap<>();
        allStops = new ArrayList<>();
        for (Line line : lines.getLines()) {
            for (int i = 0; i < line.getStops().size(); i++) {
                Stop stop = line.getStops().get(i);

                //fill allstops
                if (!allStops.contains(stop)) {
                    allStops.add(stop);
                }
                //fill graph
                if (!graph.containsKey(stop.getId())) {
                    ArrayList<Line> linesList = new ArrayList<>();
                    linesList.add(line);
                    graph.put(stop.getId(), linesList);
                    continue;
                }
                if (!graph.get(stop.getId()).contains(line)) {
                    graph.get(stop.getId()).add(line);
                }

            }
        }

    }

    public Lines getLines() {
        return lines;
    }


    public Lines calculateTravel(Stop start, Stop end) {
        Queue<Stop> queue = new LinkedList<>();
        queue.add(start);
        Map<Stop, Stop> parents = new HashMap<>();
        while (!queue.isEmpty()) {
            Stop current = queue.poll();
            if (current.equals(end)) {
                break;
            }
            for (Line line : graph.get(current.getId())) {
                for (Stop stop : line.getStops()) {
                    if (!parents.containsKey(stop)) {
                        parents.put(stop, current);
                        queue.add(stop);
                    }
                }
            }
        }

        if (!parents.containsKey(end)) {
            return null;
        }

        List<Stop> stops = new ArrayList<>();
        Stop current = end;
        while (!current.equals(start)) {
            stops.add(current);
            current = parents.get(current);
        }
        stops.add(start);

        Collections.reverse(stops);

        Lines finalRoute = new Lines();
        Line line = null;
        for (int i = 0; i < stops.size() - 1; i++) {
            Stop currentStop = stops.get(i);
            Stop nextStop = stops.get(i + 1);
            if (line == null || !line.getStops().contains(nextStop)) {
                for (Line l : graph.get(currentStop.getId())) {
                    if (l.getStops().contains(nextStop)) {
                        Line newLine = (Line) l.clone();
                        if (l.getStops().indexOf(currentStop) > l.getStops().indexOf(nextStop)){
                            for (int j = l.getStops().indexOf(currentStop); j >= l.getStops().indexOf(nextStop); j--) {
                                newLine.addStop(l.getStops().get(j));
                            }
                        } else {
                            for (int j = l.getStops().indexOf(currentStop); j <= l.getStops().indexOf(nextStop); j++) {
                                newLine.addStop(l.getStops().get(j));
                            }
                        }
                        finalRoute.addLine(newLine);
                    }
                }
            }
        }
        return finalRoute;

    }


//    GETTERS SETTERS

    public List<Stop> getAllStops() {
        return allStops;
    }



}

