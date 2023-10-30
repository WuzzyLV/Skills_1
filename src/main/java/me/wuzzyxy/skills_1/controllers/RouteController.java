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
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Object> getRoute(@PathVariable("start") int start, @PathVariable("end") int end) {
        if (start == end) {
            return new ResponseEntity<>(
                    new ObjectMapper().createObjectNode()
                            .put("error", "Start and end are the same")
                            .toString(),
                    HttpStatus.BAD_REQUEST
            );
        }
        Stop startStop = routeSystem.getLines().getStopByID(start);
        Stop endStop = routeSystem.getLines().getStopByID(end);

        if (startStop == null || endStop == null) {
            return new ResponseEntity<>(
                    new ObjectMapper().createObjectNode()
                            .put("error", "Start or end not found")
                            .toString(),
                    HttpStatus.NOT_FOUND
            );
        }

        return ResponseEntity.ok(routeSystem.calculateTravel(
                startStop,
                endStop
        ));
    }

    @GetMapping("/raw")
    public ResponseEntity<Lines> getRaw() {
        return ResponseEntity.ok(routeSystem.getLines());
    }
}
