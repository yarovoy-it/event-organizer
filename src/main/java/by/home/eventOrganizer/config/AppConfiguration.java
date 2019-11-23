package by.home.eventOrganizer.config;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("by.home.eventOrganizer")
@Import({DatabaseConfiguration.class})
public class AppConfiguration {


}
