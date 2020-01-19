package by.home.eventOrganizer.config;

import by.home.eventOrganizer.component.LocalDateConverter;
import by.home.eventOrganizer.dto.detail.OrderDto;
import by.home.eventOrganizer.model.detail.Order;
import org.dozer.CustomConverter;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldsMappingOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Web configuration.
 */
@EnableWebMvc
public class WebConfiguration {

    /**
     * Mapper mapper.
     *
     * @return the mapper
     */
    @Bean
    public Mapper mapper() {
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        List<CustomConverter> converters = new ArrayList<>();
        converters.add(new LocalDateConverter());
        dozerBeanMapper.setCustomConverters(converters);
        dozerBeanMapper.addMapping(mappingBuilder());
        return dozerBeanMapper;
    }

    private BeanMappingBuilder mappingBuilder() {
        return new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(OrderDto.class, Order.class).fields("executeDate", "executeDate", FieldsMappingOptions.customConverter(LocalDateConverter.class));
            }
        };
    }

    /**
     * Password encoder password encoder.
     *
     * @return the password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
