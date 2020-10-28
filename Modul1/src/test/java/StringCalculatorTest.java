import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {

    private StringCalculator calculator;

    @BeforeEach
    public void createCalculator() {

        calculator = new StringCalculator();
    }
    @Test
    void shouldReturnZeroWhenEmptyStringIsGiven() {
        assertEquals(0,calculator.add(""));
    }
    @Test
    void shouldReturnOneNumberWhenOneNumberIsGiven() {
        assertEquals(2,calculator.add("2"));
    }
    @Test
    void shouldReturnSumOfTheNumbersWhenTwoNumbersIsGiven() {
        assertEquals(5,calculator.add("2,3"));
        assertEquals(5,calculator.add("7,-2"));
    }
    @Test
    void shouldReturnSumOfTheNumbersWhenThreeNumbersIsGiven() {
        assertEquals(6,calculator.add("8,-2,0"));
    }
    @Test
    void shouldReturnTrueWhenSumNumbersIsEqualsExpectedValue() {
        int result=calculator.add("8,-2,0");
        int expect=6;
        boolean result2 = expect == result;
        assertEquals(true,result2);
    }
    @Test
    void assertsThatObjectIsNull() {
        if(calculator==null)
            assertNull(null);
    }
}