package net.groundgurus.customer_api_jdbc2.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(IOException.class)
    public void something() {
        System.out.println("Unable to read file");
    }

    @ExceptionHandler(RuntimeException.class)
    public void handleRuntime() {
        System.out.println("asdfasdfdsa");
    }
}
