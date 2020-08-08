package de.cgrotz.spring.status.webflux.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Deprecated
@RestController
public class TestControllerClassAnnotated {
    @GetMapping("/controller2/hello")
    public String noStatusHandler() {
        return "Hello World!";
    }
}
