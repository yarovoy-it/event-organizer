package by.home.eventOrganizer.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * The type App configuration.
 */
@Configuration
@ComponentScan("by.home.eventOrganizer")
@Import({DatabaseConfigurationH2.class, WebConfiguration.class, MessagesConfiguration.class,
        SecurityConfiguration.class, SwaggerConfiguration.class})
public class AppConfiguration {


}
