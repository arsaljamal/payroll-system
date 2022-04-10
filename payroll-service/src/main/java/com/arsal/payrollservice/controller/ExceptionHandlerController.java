package com.arsal.payrollservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@RestControllerAdvice
public class ExceptionHandlerController {
    private Logger logger = Logger.getLogger(String.valueOf(ExceptionHandlerController.class));

    @ExceptionHandler(Exception.class)
    public void handleException(Exception ex, HttpServletResponse res) throws IOException {
        logger.info("Handled Internal Error Exception : " + ex.getMessage());
        res.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }
}
