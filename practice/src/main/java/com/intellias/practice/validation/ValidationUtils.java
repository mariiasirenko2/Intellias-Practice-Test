package com.intellias.practice.validation;

import com.intellias.practice.exceptionHandling.customExceptions.EmptyStringException;
import com.intellias.practice.exceptionHandling.customExceptions.NegativeMoneyValueException;

public class ValidationUtils {
    public void emptyString(String string) throws EmptyStringException {
        if (string.isEmpty()) throw new EmptyStringException();
    }

    public void negativeNumber(double value) throws NegativeMoneyValueException {
        if (value < 0) throw new NegativeMoneyValueException();
    }
}
