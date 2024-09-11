package lt.vu.mif.nosql.rest_example;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lt.vu.mif.nosql.rest_example.dto.CarLocation;
import lt.vu.mif.nosql.rest_example.exceptions.CarNotFoundException;
import lt.vu.mif.nosql.rest_example.exceptions.IllegalLicensePlateNumberException;

import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;



@RestController
public class APIController {
    
    // Connects to Redis, assuming Redis is running on the same machine
    // (incl. exposed via Docker) with default port 6379
    private JedisPool jedisPool = new JedisPool("localhost", 6379);

    private Pattern licensePlatePattern = Pattern.compile("^[A-Z0-9]{1,7}$");

    @GetMapping("/car/location/{licenseNo}")
    public CarLocation getLocation(@PathVariable String licenseNo) throws IllegalLicensePlateNumberException, CarNotFoundException {
        validateLicenseNumber(licenseNo);

        try (Jedis jedis = jedisPool.getResource()) {
            String locationValue = jedis.get("Car:" + licenseNo);

            if (locationValue == null) { // If car location is empty (no key is set), we return 404
                throw new CarNotFoundException();
            }

            String[] locationComponents = locationValue.split(":");
            return new CarLocation(
                Double.parseDouble(locationComponents[0]), 
                Double.parseDouble(locationComponents[1])
            );
        }
    }

    @PostMapping("/car/location/{licenseNo}")
    public ResponseEntity updateLocation(@PathVariable String licenseNo, @RequestBody CarLocation carLocation) throws IllegalLicensePlateNumberException {
        validateLicenseNumber(licenseNo);
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.set("Car:" + licenseNo, "" + carLocation.getLat() + ":" + carLocation.getLng());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/car/location/{licenseNo}")
    public ResponseEntity deleteLocation(@PathVariable String licenseNo) throws IllegalLicensePlateNumberException {
        validateLicenseNumber(licenseNo);

        try (Jedis jedis = jedisPool.getResource()) {
            jedis.del("Car:" + licenseNo);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    
    private void validateLicenseNumber(String licenseNo) throws IllegalLicensePlateNumberException {
        if (!licensePlatePattern.matcher(licenseNo).matches()) {
            throw new IllegalLicensePlateNumberException();
        }
    }
    
    private String carLocationKey(String licenseNo) {
        return "Car:" + licenseNo;
    }

}
