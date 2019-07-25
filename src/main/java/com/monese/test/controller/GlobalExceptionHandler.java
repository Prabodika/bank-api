package com.monese.test.controller;

import com.monese.test.exceptions.SystemException;
import com.monese.test.exceptions.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserException.class)
    @ResponseBody
    public String handleBadRequestException(UserException exception) {
        return exception.getMessage();

    }

    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(SystemException.class)
    @ResponseBody
    public String handleSystemException(SystemException exception) {
        return exception.getMessage();

    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleException(SystemException exception) {
        return exception.getMessage();

    }

}
