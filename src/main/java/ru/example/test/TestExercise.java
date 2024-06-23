package ru.example.test;

import org.springframework.stereotype.Component;

import static ru.example.test.Constants.DIVIDING_ZERO_MESSAGE;

@Component
public class TestExercise {
    public static Double getDoubleFromTwoInt(Integer a, Integer b) {
        if (a.equals(0)) {
            throw new ArithmeticException(DIVIDING_ZERO_MESSAGE);
        } else if (a > b) {
            return (double) (a - b) / 2.0;
        } else if (a < b) {
            return (double) b / a;
        } else {
            return (double) a;
        }
    }
}
