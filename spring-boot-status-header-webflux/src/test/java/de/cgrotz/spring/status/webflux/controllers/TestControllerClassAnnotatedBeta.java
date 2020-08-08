package de.cgrotz.spring.status.webflux.controllers;

import de.cgrotz.spring.status.annotations.Status;
import de.cgrotz.spring.status.annotations.StatusValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Status(StatusValue.BETA)
@RestController
public class TestControllerClassAnnotatedBeta {
    @GetMapping("/controller4/hello")
    public String noStatusHandler() {
        return "Hello World!";
    }
}
