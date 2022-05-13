package mk.ukim.finki.wp.eLibrary.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class GenreNotFoundException extends RuntimeException {

    public GenreNotFoundException(Long id) {
        super(String.format("Genre with id: %d is not found", id));
    }

}
