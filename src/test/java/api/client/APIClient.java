package api.client;

import config.Configuration;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class APIClient {
    /** Подключение логирования. */
    private static final Logger LOG = LoggerFactory.getLogger(APIClient.class);
    private final RequestSpecification specification;

    public APIClient(RequestSpecification specification) {
        this.specification = specification;
    }

    /** Выполняет запрос на авторизацию и возвращает cookie. */
    public Map<String, String> login() {
        LOG.info("Отправка запроса на авторизацию.");
        // @formatter: off
        Map<String, String> cookie = specification
                    .contentType(ContentType.URLENC)
                    .formParam("email", Configuration.email())
                    .formParam("password", Configuration.password())
                .when()
                    .post("/login")
                .then()
                    .log().all()
                    .statusCode(302)
                    .extract().cookies();
        // @formatter: on
        LOG.info("Запрос выполнен успешно.");
        return cookie;
    }
}
