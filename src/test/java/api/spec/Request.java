package api.spec;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

/**
 * Предоставляет единую спецификацию для запросов
 */
public class Request {
    /** Возвращает спецификацию, содержащую базовый URL и фильтры. */
    public static RequestSpecification specification() {
        // @formatter: off
        return given()
                .filter(new AllureRestAssured())
                .baseUri("http://demowebshop.tricentis.com");
        // @formatter: on
    }
}
