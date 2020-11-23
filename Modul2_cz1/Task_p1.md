# Task part 1 with AssertJ.

## VatService:
```
public class VatService {
    double vatValue;
    public VatService() {
        this.vatValue = 0.23;
    }
    public double getGrossPriceForDefaultVat(Product product) throws IncorrectVatException{
        return getGrossPrice(product.getNetPrice(), vatValue);
    }
    public double getGrossPrice(double netPrice, double vatValue) throws IncorrectVatException{
        if(vatValue > 1){
            throw new IncorrectVatException("VAT must be lower!");
        }
        return netPrice * (1 + vatValue);
    }
}
```
## Product:
```
public class Product {
    String id;
    double netPrice;
    public Product(String id, double netPrice){
        this.id=id;
        this.netPrice=netPrice;
    }
    public double getNetPrice() {
        return netPrice;
    }
}
```
## Exception:
```
public class IncorrectVatException extends Throwable{
    private String message;
    public IncorrectVatException(String message){
        this.message = message;
    }
    @Override
    public String getMessage(){return message;}
}
```
## Tests:
```
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
//Using AssertJ to make tests more readable!
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
class VatServiceTest {
    VatService vatService;
    Product product;
    @Test
    @DisplayName("should calculate gross price for default VAT") //Only in version JUnit 5.
    void shouldCalculateGrossPriceForDefaultVat() throws IncorrectVatException {
        //given
        product = new Product("01",20.00);
        //then
        double result = vatService.getGrossPriceForDefaultVat(product);
        //when
        assertThat(result).isEqualTo((24.60));
    }
    @Test
    @DisplayName("should calculate gross price for other VAT value")
    void shouldCalculateGrossPriceForOtherVatValue() throws IncorrectVatException {
        //given
        product = new Product("02",10.00);
        //then
        double result = vatService.getGrossPrice(product.getNetPrice(), 0.08);
        //when
        assertThat(result).isEqualTo((10.80));
    }
    @Test
    @DisplayName("should throw exception when VAT is too high")
    void shouldThrowExceptionWhenVatIsTooHigh() {
        //given
        product = new Product("03",10.00);
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
```
