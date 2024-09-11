package lt.vu.mif.nosql.rest_example;

import org.springframework.web.bind.annotation.RestController;

import lt.vu.mif.nosql.rest_example.dto.AdditionParams;
import lt.vu.mif.nosql.rest_example.dto.AdditionResult;
import lt.vu.mif.nosql.rest_example.dto.WelcomeMessage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class APIController {
    
    @GetMapping("/hello")
    public WelcomeMessage hello() {
        return new WelcomeMessage("Hello, world!");
    }

    @PostMapping("/add")
    public AdditionResult add(@RequestBody AdditionParams additionParams) {
        return new AdditionResult(
            additionParams.getA() + additionParams.getB()
        );
    }
    
    

}
