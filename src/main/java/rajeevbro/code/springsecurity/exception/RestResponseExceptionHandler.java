package rajeevbro.code.springsecurity.exception;

import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import rajeevbro.code.springsecurity.entity.ErrorMessage;



@RestControllerAdvice
@ResponseStatus
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler
{

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorMessage> userNotFoundException(UserNotFoundException userNotFoundException)
    {
       ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND,userNotFoundException.getMessage());
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorMessage> handleAuthenticationException()
    {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND,"UserNAme or Password Incorrect");
      return   ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);

    }

}
