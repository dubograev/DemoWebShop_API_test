package cookie;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Контейнер для хранения cookie и добавления их к драйверу браузера.
 */
public class SiteCookies {
    /** Подключение логирования. */
    private static final Logger LOG = LoggerFactory.getLogger(SiteCookies.class);

    private final Map<String, String> cookies;

    public SiteCookies(Map<String, String> cookies) {
        this.cookies = cookies;
    }

    /** Добавление cookie к драйверу. */
    public void addCookiesToDriver() {
        LOG.info("Добавление cookie к браузеру.");
        cookies.entrySet()
                .stream()
                .forEach(cookie -> {
                    LOG.info("Key is {} \n Value is {}", cookie.getKey(), cookie.getValue());
                    WebDriverRunner
                            .getWebDriver()
                            .manage()
                            .addCookie(new Cookie(cookie.getKey(), cookie.getValue()));
                });
    }
}
