package de.cgrotz.spring.status.webflux;

import de.cgrotz.spring.status.annotations.Status;
import de.cgrotz.spring.status.annotations.StatusValue;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

public class WebFluxInterceptor implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        exchange.getResponse().beforeCommit(() ->{
            Object handler = exchange.getAttribute(HandlerMapping.BEST_MATCHING_HANDLER_ATTRIBUTE);
            if(handler instanceof HandlerMethod) {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                attachAnnotations(exchange, handlerMethod.getMethod());
                attachAnnotations(exchange, handlerMethod.getBeanType());
            }
            return Mono.empty();
        });
        return chain.filter(exchange);
    }

    private void attachAnnotations(ServerWebExchange exchange, AnnotatedElement annotated) {
        for (Annotation declaredAnnotation : annotated.getDeclaredAnnotations()) {
            if (declaredAnnotation.annotationType().isAssignableFrom(Deprecated.class)) {
                exchange.getResponse().getHeaders().add("Status", "DEPRECATED");
            }
            if (declaredAnnotation.annotationType().isAssignableFrom(Status.class)) {
                Status status = (Status) declaredAnnotation;
                for (StatusValue statusValue : status.value()) {
                    exchange.getResponse().getHeaders().add("Status", statusValue.name());
                }
                if (!StringUtils.isEmpty(status.additionalInfo())) {
                    exchange.getResponse().getHeaders().add("Status-Info", status.additionalInfo());
                }
            }
        }
    }
}
