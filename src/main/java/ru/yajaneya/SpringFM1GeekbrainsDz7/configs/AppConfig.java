package ru.yajaneya.SpringFM1GeekbrainsDz7.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"secrets.properties"})
public class AppConfig {
}
