package hufslikelion.debugging.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BusinessException extends ResponseStatusException {

    public BusinessException(HttpStatus status, String message) {
        super(status, message);
    }
}
