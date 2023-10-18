package me.wuzzyxy.skills_1.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.wuzzyxy.skills_1.routes.RouteSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouteController {

    @Autowired
    private RouteSystem routeSystem;

    @GetMapping("/routes")
    public String getRoutes() {
        //return routeSystem.calculateTravel(routeSystem.getLines().getStopByID(2), routeSystem.getLines().getStopByID(5));
        return "no";
    }
}
