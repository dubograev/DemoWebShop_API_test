package tests;

import api.client.APIClient;
import api.spec.Request;
import cookie.SiteCookies;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class TestBase {

    private Map<String, String> cookie;
    private SiteCookies siteCookies;
    private APIClient client;

    public Map<String, String> getCookie() {
        return cookie;
    }

    @BeforeAll
    static void beforeAll() {
        RestAssured.filters(new AllureRestAssured());
    }

    @BeforeEach
    public void logInAndGetCookies() {
        step("Авторизация на сайте и сохранение cookie", () -> {
            step("Инициализация api-клиента", () -> {
                client = new APIClient(Request.specification());
            });
            step("Авторизация и получение cookie", () -> {
                cookie = client.login();
            });
            step("Переход на страницу в браузере и добавление cookie", () -> {
                step("Открыть /Themes/DefaultClean/Content/images/logo.png", () -> {
                    open("http://demowebshop.tricentis.com/Themes/DefaultClean/Content/images/logo.png");
                });
                step("Добавить cookie в сессию браузера", () -> {
                    siteCookies = new SiteCookies(cookie);
                    siteCookies.addCookiesToDriver();
                });
            });
        });
    }
}
