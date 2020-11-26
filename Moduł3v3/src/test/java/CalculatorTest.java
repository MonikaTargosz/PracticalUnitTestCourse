import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//Using AssertJ to make tests more readable!
import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    @DisplayName("should return 0 when empty string")
    public void shouldReturnZeroWhenGoEmptyString(){
        //given
        //when
        int result = calculator.add("");
        //then
        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("should return number when given number")
    void shouldReturnNumberWhenGivenNumber() {
        //given
        //when
        int result = calculator.add("1");
        //then
        //Nie przechodzi, bo jest 0
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("should add two numbers separate with comma")
    void shouldAddTwoNumbersSeparatedWithComma() {
        //when
        int result = calculator.add("1,2");
        //then
        assertThat(result).isEqualTo(3);
    }

    @Test
    @DisplayName("should add three numbers separate with comma")
    void shouldAddThreeNumbersSeparatedWithComma() {
        //when
        int result = calculator.add("1,2,3");
        //then
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("should add three numbers separate with comma and new line")
    void shouldAddThreeNumbersSeparatedWithCommaAndNewLine() {
        int result = calculator.add("1,2\n4");
        //then
        assertThat(result).isEqualTo(7);
    }

    @Test
    @DisplayName("should throw exception when negative number provided")
    void shouldThrowExceptionWhenNegativeNumberProvided() {
        Assertions.assertThrows(NegativeNumberExpection.class, ()->{
            calculator.add("-1");
        });
    }

    @Test
    @DisplayName("should included all negative numbers in exception message")
    void shouldIncludeAllNegativeNumbersInExceptionMessage() {
        NegativeNumberExpection expection = Assertions.assertThrows(NegativeNumberExpection.class, ()->{
            calculator.add("-1,-2");
        });
        assertThat(expection.getMessage()).isEqualTo("Negatives: -1 -2");
    }
}
