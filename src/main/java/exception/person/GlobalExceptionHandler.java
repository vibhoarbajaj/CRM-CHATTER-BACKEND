package exception.person;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(value = {ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(ResourceNotFoundException resourceNotFoundException) {
        System.out.println("AA raha hai");
        PersonApiResponse personApiResponse = new PersonApiResponse(
                resourceNotFoundException.getMessage(),
                false
        );
      //  return ResponseEntity.status(HttpStatus.NOT_FOUND).body(personApiResponse);
   return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

}
