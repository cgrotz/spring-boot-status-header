package de.cgrotz.spring.status.webmvc.controllers;

import de.cgrotz.spring.status.annotations.Status;
import de.cgrotz.spring.status.annotations.StatusValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Deprecated
@RestController
public class TestControllerMixed {

    @GetMapping("/controller5/no_status")
    public String noStatusHandler() {
        return "Hello World!";
    }

    @GetMapping("/controller5/beta")
    @Status(StatusValue.BETA)
    public String beta() {
        return "Hello World!";
    }

    @GetMapping("/controller5/alpha")
    @Status(StatusValue.ALPHA)
    public String alpha() {
        return "Hello World!";
    }

    @GetMapping("/controller5/alphaWithAdditionalInfo")
    @Status(value = StatusValue.ALPHA, additionalInfo = "Beta on 01.01.2021")
    public String alphaWithAdditionalInfo() {
        return "Hello World!";
    }
}
