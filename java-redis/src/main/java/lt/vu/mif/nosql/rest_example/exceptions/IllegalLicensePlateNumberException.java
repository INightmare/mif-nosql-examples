package lt.vu.mif.nosql.rest_example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST) // 400
public class IllegalLicensePlateNumberException extends Exception {
    
}
