package com.intellias.practice.exceptionHandling.customExceptions;

public class NegativeMoneyValueException extends Exception{
    public NegativeMoneyValueException(String errorMessage) {
        super(errorMessage);
    }

    public NegativeMoneyValueException() {
    }
}
