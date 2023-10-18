package me.wuzzyxy.skills_1.config;

import me.wuzzyxy.skills_1.routes.RouteSystem;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class SingletonConfig {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public RouteSystem routeSystem() {
        return new RouteSystem();
    }

}
