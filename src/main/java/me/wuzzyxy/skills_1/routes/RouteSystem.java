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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RouteSystem {

    Logger logger = LoggerFactory.getLogger(RouteSystem.class);

    private Lines lines;

    public RouteSystem() {
        try {
            lines = new ObjectMapper().readValue(
                    ResourceUtils.getFile("classpath:routes.json"),
                    Lines.class
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Map<Integer, List<Line>> lineGraph = new HashMap<>();
        for (Line line : lines.getLines()) {
            for (int i = 0; i < line.getStops().size(); i++) {
                Stop stop = line.getStops().get(i);
                if (!lineGraph.containsKey(stop.getId())) {
                    ArrayList<Line> linesList = new ArrayList<>();
                    linesList.add(line);
                    lineGraph.put(stop.getId(), linesList);
                    continue;
                }
                if (!lineGraph.get(stop.getId()).contains(line)) {
                    lineGraph.get(stop.getId()).add(line);
                }
            }
        }

        //print stopLinesMap
        for (Map.Entry<Integer, List<Line>> entry : lineGraph.entrySet()) {
            logger.info(entry.getKey() + " " + entry.getValue());
        }
    }

    public Lines getLines() {
        return lines;
    }


    public String calculateTravel(Map<Integer, List<Line>> graph, Stop start, Stop end) {



        return null;
    }



}

