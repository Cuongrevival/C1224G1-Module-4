package org.example.logger;

import org.example.exception.BadWordException;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@Aspect
@Component
public class BadWordLoggingAspect {

    private static final Logger logger = Logger.getLogger(BadWordLoggingAspect.class.getName());

    @AfterThrowing(pointcut = "execution(* org.example.service.FeedBackService.save(..))", throwing = "ex")
    public void logBadWordViolation(Exception ex) {
        if (ex instanceof BadWordException) {
            BadWordException badWordEx = (BadWordException) ex;
            logger.warning(String.format(
                    "[%s] Feedback rejected | Author: %s | Comment: %s",
                    LocalDateTime.now(),
                    badWordEx.getAuthor(),
                    badWordEx.getComment()
            ));
        }
    }
}
