import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

class VatServiceTest {

    VatService vatService;
    Product product;

    @Test
    @DisplayName("should calculate gross price for default VAT")
    void shouldCalculateGrossPriceForDefaultVat() throws IncorrectVatException {
        //given
        product = new Product("Clothes",20.00);
        //then
        double result = vatService.getGrossPriceForDefaultVat(product);
        //when
        assertThat(result).isEqualTo((24.60));
        assertEquals(24.60, result);
    }


    @Test
    @DisplayName("should calculate gross price for other VAT value")
    void shouldCalculateGrossPriceForOtherVatValue() throws IncorrectVatException {
        //given
        product = new Product("Book",10.00);
        //then
        double result = vatService.getGrossPrice(product.getNetPrice(), 0.08);
        //when
        assertThat(result).isEqualTo((10.80));
        assertEquals(10.80, result);
    }

    @Test
    @DisplayName("should throw exception when VAT is too high")
    void shouldThrowExceptionWhenVatIsTooHigh() {
        //given
        product = new Product("Cosmetic",10.00);
        //then

        //when
        assertThatExceptionOfType(IncorrectVatException.class).isThrownBy(() -> {
            vatService.getGrossPrice(product.getNetPrice(), 10);
        }).withMessage(("VAT must be lower!"));;
    }

    @BeforeEach
    void prepareVatService(){
        vatService = new VatService();
    }
}