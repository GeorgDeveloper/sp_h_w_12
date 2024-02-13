package ru.georgdeveloper.taskapp.logg;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserActionLoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(UserActionLoggingAspect.class);

    @Pointcut(value = "@annotation(ru.georgdeveloper.taskapp.anatation.TrackUserAction)")
    public void trackUserAction() {}

    @AfterReturning(pointcut = "trackUserAction()", returning = "result")
    public void logUserAction(JoinPoint joinPoint, Object result) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        UserActionLogger.logUserAction(username, methodName, args);
    }
}
