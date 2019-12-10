package by.home.eventOrganizer.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer {

    @Bean
    public Mapper mapper() {
        return new DozerBeanMapper();
    }


}
