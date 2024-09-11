package lt.vu.mif.nosql.rest_example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // 404
public class CarNotFoundException extends Exception {
    
}
