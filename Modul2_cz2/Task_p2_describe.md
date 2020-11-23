# Task part 2 with Mockito.

## VatService:
```
public class VatService {
    VatProvider vatProvider;
    public VatService(VatProvider vatProvider){this.vatProvider = vatProvider;}
    public double getGrossPriceForDefaultValue(Product product) throws IncorrectVatException {
        return calculateGrossPrice(product.getNetPrice(), vatProvider.getDefaultVat());
    }
    public double getGrossPrice(double netPrice, String productType) throws IncorrectVatException {
        double vatValue = vatProvider.getVatForType(productType);
        return calculateGrossPrice(netPrice, vatValue);
    }
    private double calculateGrossPrice(double netPrice, double vatValue) throws IncorrectVatException {
        if (vatValue > 1.0) {
            throw new IncorrectVatException("VAT must be lower!");
        }
        return (netPrice * (1.0 + vatValue));
    }
}
```
## Product:
```
public class Product {
    String id;
    double netPrice;
    String type;
    public Product(String id, double netPrice, String type) {
        this.id = id;
        this.netPrice = netPrice;
        this.type = type;
    }
    public double getNetPrice() {
        return netPrice;
    }
}
```
## VatProvider:
```
interface VatProvider {
    double getDefaultVat();
    double getVatForType(String type);
}
```
## Tests:
```
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
//Using AssertJ to make tests more readable!
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;
class VatServiceTest {
    VatService vatService;
    VatProvider vatProvider;
    Product product;
    @Test
    @DisplayName("should calculate gross price for default VAT") //Only in version JUnit 5.
    void shouldCalculateGrossPrice() throws IncorrectVatException {
        //given
        when(vatProvider.getDefaultVat()).thenReturn(0.23);
        product = new Product("01", 20.0, "Books");
        //when
        double result = vatService.getGrossPriceForDefaultValue(product);
        //then
        assertThat(result).isEqualTo(24.60);
    }
    @Test
    @DisplayName("should calculate gross price for other VAT value")
    void shouldCalculateGrossPriceForOtherVatValue() throws IncorrectVatException {
        //given
        String type = "Food";
        product = new Product("02", 10.00, type);
        when(vatProvider.getVatForType(type)).thenReturn(0.08);
        //when
        double result = vatService.getGrossPrice(product.getNetPrice(), type);
        //then
        assertThat(result).isEqualTo(10.80);
    }
    @Test
    @DisplayName("should throw exception when VAT is too high")
    void shouldThrowExceptionWhenVatIsTooHigh() {
        //given
        String type = "Clothes";
        product = new Product("03", 10.00, type);
        when(vatProvider.getVatForType(type)).thenReturn(10.00);
        //then
        assertThatExceptionOfType(IncorrectVatException.class).isThrownBy(() -> {
            vatService.getGrossPrice(product.getNetPrice(), type);
        });
    }
    @BeforeEach
    void prepareVatService() {
        vatProvider = Mockito.mock(VatProvider.class);
        vatService = new VatService(vatProvider);
    }
}
```
