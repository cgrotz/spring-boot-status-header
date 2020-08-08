package de.cgrotz.spring.status.example.webmvc;

import de.cgrotz.spring.status.annotations.Status;
import de.cgrotz.spring.status.annotations.StatusValue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Deprecated
@RestController
@SpringBootApplication
public class ExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }

    @Status(StatusValue.BETA)
    @GetMapping("/")
    public String get() {
        return "Hello World!";
    }
}
