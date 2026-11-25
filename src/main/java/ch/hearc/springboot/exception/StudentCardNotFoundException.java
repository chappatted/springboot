package ch.hearc.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class StudentCardNotFoundException extends RuntimeException {

    public StudentCardNotFoundException(Long id) {
        super("Student card not found: " + id);
    }
}
