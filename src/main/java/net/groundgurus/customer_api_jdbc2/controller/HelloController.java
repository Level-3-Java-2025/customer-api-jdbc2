package net.groundgurus.customer_api_jdbc2.controller;

import net.groundgurus.customer_api_jdbc2.model.MyMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
public class HelloController {
//    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @GetMapping
    public ResponseEntity<MyMessage> sayHello() {
        return new ResponseEntity<>(new MyMessage("Hello World"), HttpStatus.ACCEPTED);
    }
}
