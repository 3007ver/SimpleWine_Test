package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:properties/credentials.properties"
})
public interface CredentialsConfig extends Config {
    @Config.Key("emailAccount")
    String emailAccount ();

    @Config.Key("passwordAccount")
    String passwordAccount ();

    @Config.Key("name")
    String name ();
}
