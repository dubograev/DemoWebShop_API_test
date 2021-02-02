package tests;

import io.qameta.allure.*;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@Owner("Andrey Dubograev")
@Feature("Добавление товара в корзину")
public class AddToCartAPITests extends TestBase{

    @ParameterizedTest(name = "{index} параметризованный тест")
    @ValueSource(ints = {52, 53, 65})
    @DisplayName("DisplayName - Добавление desktop-компьютера в корзину c использованием @ParameterizedTest и @ValueSource")
    @Severity(SeverityLevel.NORMAL)
    @Description("Description - какое-то описание")
    @Disabled
    public void addDesktopToCartValueSourceTest(int processor) {
        String body = "product_attribute_72_5_18=" + processor +
                "&product_attribute_72_6_19=91" +
                "&product_attribute_72_3_20=57" +
                "&addtocart_72.EnteredQuantity=1";

        step("Проверка добавления компьютера в корзину с процессором ${processor}", () -> {
        given()
                .body(body)
                .contentType(ContentType.URLENC)
                .cookie(String.valueOf(getCookie()))
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
                .then()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"));
        });
    }

    @DisplayName("Добавление desktop-компьютера в корзину c использованием @ParameterizedTest и @CsvSource")
    @ParameterizedTest(name = "{index} Processor ''{0}'' (значение атрибута {1})")
    @CsvSource({"Slow, 52", "Medium, 53", "Fast, 65"})
    public void addDesktopToCartWithDifferentProcessorCsvSourceTest(String proc, int processor) {
        String body = "product_attribute_72_5_18=" + processor +
                "&product_attribute_72_6_19=91" +
                "&product_attribute_72_3_20=57" +
                "&addtocart_72.EnteredQuantity=1";

        step("Проверка добавления компьютера в корзину с процессором " + proc , () -> {
            given()
                    .body(body)
                    .contentType(ContentType.URLENC)
                    .cookie(String.valueOf(getCookie()))
                    .when()
                    .post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
                    .then()
                    .statusCode(200)
                    .body("success", is(true))
                    .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"));
        });
    }

    @ParameterizedTest(name = "RAM ''{0}'' (значение атрибута {1})")
    @CsvSource({"8 GB, 91", "2 GB, 54", "4 GB, 55"})
    @DisplayName("DisplayName - Добавление desktop-компьютера c разной оперативкой в корзину c использованием @ParameterizedTest и @CsvSource")
    @Story("Story - Добавление desktop-компьютера c разной оперативкой в корзину c использованием @ParameterizedTest и @CsvSource")
    @Severity(SeverityLevel.NORMAL)
    @Description("Description - какое-то описание")
    public void addDesktopToCartWithDifferentRamCsvSourceTest(String ramName, int ram) {
        Allure.parameter("Память", ramName);

        step("Проверка добавления компьютера в корзину с памятью " + ram, (step) -> {
            step.parameter("объем памяти", ramName);
            String body = "product_attribute_72_5_18=53" +
                    "&product_attribute_72_6_19=" + ram +
                    "&product_attribute_72_3_20=57" +
                    "&product_attribute_72_8_30=93" +
                    "&product_attribute_72_8_30=94" +
                    "&addtocart_72.EnteredQuantity=1";

            given()
                    .body(body)
                    .contentType(ContentType.URLENC)
                    .cookie(String.valueOf(getCookie()))
                    .when()
                    .post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
                    .then()
                    .statusCode(200)
                    .body("success", is(true))
                    .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"));
        });
    }

    @DisplayName("DisplayName - Добавление desktop-компьютера в корзину c разным дополнительным ПО c использованием @ParameterizedTest и @CsvSource")
    @Story("Story - Добавление desktop-компьютера в корзину с разным дополнительным ПО c использованием @ParameterizedTest и @CsvSource")
    @Severity(SeverityLevel.NORMAL)
    @Description("Description - Добавление desktop-компьютера в корзину с разным дополнительным ПО c использованием @ParameterizedTest и @CsvSource")
    @ParameterizedTest(name = "Software ''{0}'' (значение атрибута {1})")
    @CsvSource({"Image Viever, 93", "Office Suite, 94", "Other Office Suite, 95"})
    public void addDesktopToCartWithDifferentSoftwareCsvSourceTest(String softwareName, int software) {
        Allure.parameter("ПО", softwareName);

        step("Проверка добавления компьютера в корзину с памятью " + software, (step) -> {
            step.parameter("ПО", softwareName);
            String body = "product_attribute_72_5_18=65" +
                    "&product_attribute_72_6_19=55" +
                    "&product_attribute_72_3_20=58" +
                    "&product_attribute_72_8_30=" + software +
                    "&addtocart_72.EnteredQuantity=3";

            given()
                    .body(body)
                    .contentType(ContentType.URLENC)
                    .cookie(String.valueOf(getCookie()))
                    .when()
                    .post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
                    .then()
                    .statusCode(200)
                    .body("success", is(true))
                    .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"));
        });
    }
}
