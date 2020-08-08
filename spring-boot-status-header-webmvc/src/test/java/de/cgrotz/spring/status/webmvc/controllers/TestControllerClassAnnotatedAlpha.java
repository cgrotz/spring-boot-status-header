package de.cgrotz.spring.status.webmvc.controllers;

import de.cgrotz.spring.status.annotations.Status;
import de.cgrotz.spring.status.annotations.StatusValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Status(StatusValue.ALPHA)
@RestController
public class TestControllerClassAnnotatedAlpha {
    @GetMapping("/controller3/hello")
    public String noStatusHandler() {
        return "Hello World!";
    }
}
