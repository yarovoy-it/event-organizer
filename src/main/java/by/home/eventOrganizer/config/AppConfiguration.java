package by.home.eventOrganizer.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("by.home.eventOrganizer")
@Import({DatabaseConfiguration.class, WebConfiguration.class, MessagesConfiguration.class, SecurityConfiguration.class})
public class AppConfiguration {


}
