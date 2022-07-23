package com.intellias.practice.exceptionHandling.handler;

import com.intellias.practice.exceptionHandling.customExceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NegativeMoneyValueException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorInfo handleNegativeMoneyValueException(HttpServletRequest request) {
        return new ErrorInfo(HttpStatus.BAD_REQUEST, "Numeric value cannot be less than 0");
    }

    @ExceptionHandler(EmptyStringException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorInfo handleEmptyStringException(HttpServletRequest request) {
        return new ErrorInfo(HttpStatus.BAD_REQUEST, "String value cannot be empty or null");
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorInfo handleUserNotFoundException(HttpServletRequest request) {
        return new ErrorInfo(HttpStatus.NOT_FOUND, "User with such ID does not exist");
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorInfo handleProductNotFoundException(HttpServletRequest request) {
        return new ErrorInfo(HttpStatus.NOT_FOUND, "Product with such ID does not exist");
    }

    @ExceptionHandler(NoMoneyException.class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    @ResponseBody
    public ErrorInfo handleNoMoneyException(HttpServletRequest request) {
        return new ErrorInfo(HttpStatus.I_AM_A_TEAPOT, "Not enough money to by this product");
    }

}
