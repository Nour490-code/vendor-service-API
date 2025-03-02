package ecommerce.app.vendor_service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class APIExceptionHandler extends RuntimeException {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public Map<String,String> handleDatabaseException(EntityNotFoundException ex){
        Map<String,String> error = new HashMap<>();
        error.put("errorMessage", ex.getMessage());
        return error;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntityAlreadyExists.class)
    public Map<String,String> handleSignUpException(EntityAlreadyExists ex){
        Map<String,String> error = new HashMap<>();
        error.put("errorMessage", ex.getMessage());
        return error;
    }
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(PermissionDeniedException.class)
    public Map<String,String> handlePermissionDeniedException(PermissionDeniedException ex){
        Map<String,String> error = new HashMap<>();
        error.put("errorMessage", ex.getMessage());
        return error;
    }
}
