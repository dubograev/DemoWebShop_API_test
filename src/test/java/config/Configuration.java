package config;

import org.aeonbits.owner.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Предоставляет тестовые данные из пропертей.
 */
public class Configuration {
    /** Подключение логирования. */
    private static final Logger LOG = LoggerFactory.getLogger(Configuration.class);

    /** Возвращает e-mail пользователя. */
    public static String email() {
        return load().email();
    }

    /** Возвращает пароль пользователя. */
    public static String password() {
        return load().password();
    }

    /** Инициализирует конфигурацию. */
    private static TestData load() {
        LOG.info("Инициализация конфига.");
        TestData testData = ConfigFactory.create(TestData.class);
        LOG.info("Конфиг успешно инициализирован.");
        return testData;
    }
}
