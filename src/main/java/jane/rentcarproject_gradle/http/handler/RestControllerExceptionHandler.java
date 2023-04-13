package jane.rentcarproject_gradle.http.handler;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(basePackages = "jane.rentcarproject_gradle.http.rest")
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

}
