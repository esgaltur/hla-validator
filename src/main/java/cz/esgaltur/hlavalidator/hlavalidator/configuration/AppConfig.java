package cz.esgaltur.hlavalidator.hlavalidator.configuration;

import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.spring.SpringFxWeaver;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableAutoConfiguration

public class AppConfig {
    /**
     * @param applicationContext Spring Application Context
     * @return FxWeaver Platform Bean
     */
    @Bean
    FxWeaver fxWeaver(ConfigurableApplicationContext applicationContext) {
        return new SpringFxWeaver(applicationContext);
    }

    /**
     * @return RestTemplate
     */
    @Bean
    public RestTemplate restTesmplate() {
        return new RestTemplate();
    }

}
