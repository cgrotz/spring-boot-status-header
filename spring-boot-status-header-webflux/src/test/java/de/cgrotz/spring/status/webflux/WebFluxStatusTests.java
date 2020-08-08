package de.cgrotz.spring.status.webflux;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@WebFluxTest
public class WebFluxStatusTests {

    @Autowired
    private WebTestClient webClient;

    @Test
    public void testControllers() throws Exception {
        webClient.get().uri("/no_status")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().doesNotExist("Status");

        webClient.get().uri("/deprecated")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Status", "DEPRECATED");

        webClient.get().uri("/alpha")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Status", "ALPHA");

        webClient.get().uri("/beta")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Status", "BETA");

        webClient.get().uri("/alphaWithAdditionalInfo")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Status", "ALPHA")
                .expectHeader().valueEquals("Status-Info", "Beta on 01.01.2021");
    }

    @Test
    public void testControllersClass() throws Exception {
        webClient.get().uri("/controller2/hello")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Status", "DEPRECATED");

        webClient.get().uri("/controller3/hello")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Status", "ALPHA");

        webClient.get().uri("/controller4/hello")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Status", "BETA");
    }

    @Test
    public void testControllersMixed() throws Exception {
        webClient.get().uri("/controller5/no_status")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Status", "DEPRECATED");

        webClient.get().uri("/controller5/alpha")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Status", "ALPHA", "DEPRECATED");

        webClient.get().uri("/controller5/beta")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Status","BETA", "DEPRECATED");

        webClient.get().uri("/controller5/alphaWithAdditionalInfo")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Status","ALPHA", "DEPRECATED")
                .expectHeader().valueEquals("Status-Info", "Beta on 01.01.2021");
    }
}
