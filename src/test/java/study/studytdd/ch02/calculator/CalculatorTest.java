package study.studytdd.ch02.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    @DisplayName("곱셈 기능 검증")
    void multiply() {
        int result = Calculator.multiply(1, 2);
        Assertions.assertEquals(2, result);
        Assertions.assertEquals(4, Calculator.multiply(result, 2));
    }
}
