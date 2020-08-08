package de.cgrotz.spring.status.webflux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringStatusWebFluxAutoConfiguration {
    @Bean
    public WebFluxInterceptor statusWebFluxInterceptor() {
        return new WebFluxInterceptor();
    }
}
