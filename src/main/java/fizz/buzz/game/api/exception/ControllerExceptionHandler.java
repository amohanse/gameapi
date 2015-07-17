package fizz.buzz.game.api.exception;

import fizz.buzz.game.api.exception.GameException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ControllerExceptionHandler {
    private static final String CHECK_INPUTS = "Check the input and try again..";

    @ExceptionHandler(value={GameException.class})
    @ResponseBody
    public String badRequest(Exception ex, HttpServletResponse response) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return CHECK_INPUTS;
    }
}