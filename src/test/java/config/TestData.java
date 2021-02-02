package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:data.properties",
})
public interface TestData extends Config {
    @Key("email")
    String email();
    @Key("password")
    String password();
}
