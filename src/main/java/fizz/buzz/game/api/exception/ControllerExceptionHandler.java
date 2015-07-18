package fizz.buzz.game.api.exception;

import fizz.buzz.game.api.exception.GameException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ControllerExceptionHandler {
    private static final String SYSTEM_FAILURE = "Oops...Some thing went wrong..!";

    @ExceptionHandler(value={GameException.class})
    @ResponseBody
    public String systemError(Exception ex, HttpServletResponse response) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return SYSTEM_FAILURE;
    }
}