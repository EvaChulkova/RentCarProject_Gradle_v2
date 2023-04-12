package jane.rentcarproject_gradle.http.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler /*extends ResponseEntityExceptionHandler*/ {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception exception) {
        log.error("Failed to return response", exception);
        return "error/error500";
    }
}