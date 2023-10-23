package me.wuzzyxy.skills_1.routes;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.wuzzyxy.skills_1.routes.lines.Line;
import me.wuzzyxy.skills_1.routes.lines.Lines;
import me.wuzzyxy.skills_1.routes.lines.Stop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class RouteSystem {

    Logger logger = LoggerFactory.getLogger(RouteSystem.class);

    private Lines lines;
    private Map<Integer, List<Line>> graph;

    public RouteSystem() {
        try {
            lines = new ObjectMapper().readValue(
                    ResourceUtils.getFile("classpath:routes1.json"),
                    Lines.class
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        graph = new HashMap<>();
        for (Line line : lines.getLines()) {
            for (int i = 0; i < line.getStops().size(); i++) {
                Stop stop = line.getStops().get(i);
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
        //print stopLinesMap
        for (Map.Entry<Integer, List<Line>> entry : graph.entrySet()) {
            logger.info(entry.getKey() + " " + entry.getValue());
        }

        Lines route = calculateTravel(lines.getStopByID(7), lines.getStopByID(3));

        System.out.println(route.toString());
        for (Line line : route.getLines()) {
            System.out.println(line.getStops().toString());
        }

    }

    public Lines getLines() {
        return lines;
    }


    public Lines calculateTravel(Stop start, Stop end) {
        Queue<Stop> queue = new LinkedList<>();
        queue.add(start);
        Map<Stop, Stop> parents = new HashMap<>();
        logger.info("BFS");
        while (!queue.isEmpty()) {
            Stop current = queue.poll();
            if (current.equals(end)) {
                break;
            }
            for (Line line : graph.get(current.getId())) {
                logger.info("line: " + line.getId());
                for (Stop stop : line.getStops()) {
                    logger.info("stop: " + stop.getId());
                    if (!parents.containsKey(stop)) {
                        parents.put(stop, current);
                        queue.add(stop);
                        logger.info("added " + current.getId() + ":" + stop.getId());
                    }
                }
            }
        }

        //print parents
        logger.info("parents:");
        for (Map.Entry<Stop, Stop> entry : parents.entrySet()) {
            logger.info(entry.getKey().getId() + " " + entry.getValue().getId());
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

        //print stops
        logger.info("stops:");
        for (Stop stop : stops) {
            logger.info(String.valueOf(stop.getId()));
        }

        Lines finalRoute = new Lines();
        Line line = null;
        for (int i = 0; i < stops.size() - 1; i++) {
            Stop currentStop = stops.get(i);
            Stop nextStop = stops.get(i + 1);
            if (line == null || !line.getStops().contains(nextStop)) {
                for (Line l : graph.get(currentStop.getId())) {
                    if (l.getStops().contains(nextStop)) {
                        System.out.println(l.getStops().toString());
                        Line newLine = (Line) l.clone();
                        for (int j = l.getStops().indexOf(currentStop); j < l.getStops().indexOf(nextStop); j++) {// check if num bigger than other then go down if not up and then should be right order
                            newLine.addStop(l.getStops().get(j));
                        }
                        finalRoute.addLine(newLine);
                    }
                }
            }
        }
        return finalRoute;

    }



}

