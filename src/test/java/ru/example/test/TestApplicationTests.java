package ru.example.test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TestApplicationTests {

    public static Stream<Arguments> provideInputAndExpectedValues() {
        //arrange
        return Stream.of(
                //a > b
                Arguments.of(8, 3, 2.5),
                Arguments.of(7, -14, 10.5),
                Arguments.of(-7, -14, 3.5),
                Arguments.of(Integer.MAX_VALUE, 3, 1.073741822E9),
                Arguments.of(8, Integer.MIN_VALUE, -1.07374182E9),
                // a < b
                Arguments.of(7, 14, 2),
                Arguments.of(-8, 3, -0.375),
                Arguments.of(Integer.MIN_VALUE, 3, -1.3969838619232178E-9),
                Arguments.of(Integer.MIN_VALUE, Integer.MAX_VALUE, -0.9999999995343387),
                // a == b
                Arguments.of(8, 8, 8),
                Arguments.of(-8, -8, -8),
                Arguments.of(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE),
                Arguments.of(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE)
        );
    }

    @ParameterizedTest
    @MethodSource("provideInputAndExpectedValues")
    void getDoubleFromTwoIntTest(int inputA, int inputB, double expected) {
        //act
        double actual = TestExercise.getDoubleFromTwoInt(inputA, inputB);
        //assert
        assertEquals(expected, actual);
    }

    public static Stream<Arguments> provideValueForZeroDividing() {
        //arrange
        return Stream.of(
                Arguments.of(0, 0),
                Arguments.of(-0, -0),
                Arguments.of(0, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("provideValueForZeroDividing")
    void getExceptionsForDividingZeroFromGetDoubleFromTwoIntTest(int inputA, int inputB) {
        //act
        String actualMessage = assertThrows(ArithmeticException.class, () -> TestExercise.getDoubleFromTwoInt(inputA, inputB),
                "Неправильный тип Exception").getMessage();
        //assert
        assertTrue(actualMessage.contains(Constants.DIVIDING_ZERO_MESSAGE));
    }
}
