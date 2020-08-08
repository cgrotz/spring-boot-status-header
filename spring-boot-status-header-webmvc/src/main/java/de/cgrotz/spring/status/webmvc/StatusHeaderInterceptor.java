package de.cgrotz.spring.status.webmvc;

import de.cgrotz.spring.status.annotations.Status;
import de.cgrotz.spring.status.annotations.StatusValue;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

public class StatusHeaderInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            attachAnnotations(response, handlerMethod.getMethod());
            attachAnnotations(response, handlerMethod.getBeanType());
        }
        return true;
    }

    private void attachAnnotations(HttpServletResponse response, AnnotatedElement annotated) {
        for (Annotation declaredAnnotation : annotated.getDeclaredAnnotations()) {
            if (declaredAnnotation.annotationType().isAssignableFrom(Deprecated.class)) {
                response.addHeader("Status", "DEPRECATED");
            }
            if (declaredAnnotation.annotationType().isAssignableFrom(Status.class)) {
                Status status = (Status) declaredAnnotation;
                for (StatusValue statusValue : status.value()) {
                    response.addHeader("Status", statusValue.name());
                }
                if (!StringUtils.isEmpty(status.additionalInfo())) {
                    response.addHeader("Status-Info", status.additionalInfo());
                }
            }
        }
    }
}
