package de.cgrotz.spring.status.webmvc.controllers;

import de.cgrotz.spring.status.annotations.Status;
import de.cgrotz.spring.status.annotations.StatusValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestControllerOnlyMethodsAnnotated {

    @GetMapping("/no_status")
    public String noStatusHandler() {
        return "Hello World!";
    }

    @GetMapping("/deprecated")
    @Deprecated
    public String deprecated() {
        return "Hello World!";
    }

    @GetMapping("/beta")
    @Status(StatusValue.BETA)
    public String beta() {
        return "Hello World!";
    }

    @GetMapping("/alpha")
    @Status(StatusValue.ALPHA)
    public String alpha() {
        return "Hello World!";
    }

    @GetMapping("/alphaWithAdditionalInfo")
    @Status(value = StatusValue.ALPHA, additionalInfo = "Beta on 01.01.2021")
    public String alphaWithAdditionalInfo() {
        return "Hello World!";
    }
}
