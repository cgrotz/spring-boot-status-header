package de.cgrotz.spring.status.webflux.controllers;

import de.cgrotz.spring.status.annotations.Status;
import de.cgrotz.spring.status.annotations.StatusValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Deprecated
@RestController
public class TestControllerMixed {

    @GetMapping("/controller5/no_status")
    public Mono<String> noStatusHandler() {
        return Mono.just("Hello World!");
    }

    @GetMapping("/controller5/beta")
    @Status(StatusValue.BETA)
    public Mono<String> beta() {
        return Mono.just("Hello World!");
    }

    @GetMapping("/controller5/alpha")
    @Status(StatusValue.ALPHA)
    public Mono<String> alpha() {
        return Mono.just("Hello World!");
    }

    @GetMapping("/controller5/alphaWithAdditionalInfo")
    @Status(value = StatusValue.ALPHA, additionalInfo = "Beta on 01.01.2021")
    public Mono<String> alphaWithAdditionalInfo() {
        return Mono.just("Hello World!");
    }
}
