package mk.ukim.finki.wp.eLibrary.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class BookNoLongerAvailableException extends RuntimeException {

    public BookNoLongerAvailableException(String name) {
        super(String.format("Book: %s is no longer available.", name));
    }

}
