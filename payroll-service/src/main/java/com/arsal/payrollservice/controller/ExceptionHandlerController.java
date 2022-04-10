package com.arsal.payrollservice.controller;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@RestControllerAdvice
public class ExceptionHandlerController {
    private Logger logger = Logger.getLogger(String.valueOf(ExceptionHandlerController.class));

    private Gson gson = new Gson();

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex, HttpServletResponse res) throws IOException {
        logger.info("Handled Internal Error Exception : " + ex.getMessage());
        return new ResponseEntity<>(gson.toJson(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
