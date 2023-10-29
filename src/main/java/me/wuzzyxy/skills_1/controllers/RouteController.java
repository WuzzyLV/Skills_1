package me.wuzzyxy.skills_1.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.wuzzyxy.skills_1.routes.RouteSystem;
import me.wuzzyxy.skills_1.routes.lines.Line;
import me.wuzzyxy.skills_1.routes.lines.Lines;
import me.wuzzyxy.skills_1.routes.lines.Stop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class RouteController {

    @Autowired
    private RouteSystem routeSystem;

    @GetMapping("/")
    public ResponseEntity index() {
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/routes")).build();
    }

    @GetMapping("/routes")
    public List<Stop> getRoutes() {
        return routeSystem.getAllStops();
    }

    @GetMapping("/route/{start}/{end}")
    public Lines getRoute(@PathVariable("start") int start, @PathVariable("end") int end) {
        if (start == end) {
            return new Lines();
        }

        return routeSystem.calculateTravel(
                routeSystem.getLines().getStopByID(start),
                routeSystem.getLines().getStopByID(end)
        );
    }
}
