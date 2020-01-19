package by.home.eventOrganizer.configuration;

import by.home.eventOrganizer.component.LocalDateConverter;
import by.home.eventOrganizer.dto.detail.OrderDto;
import by.home.eventOrganizer.model.detail.Order;
import org.dozer.CustomConverter;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldsMappingOptions;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Test web configuration.
 */
@Configuration
@ComponentScan(basePackages = "by.home.eventOrganizer")
@EnableWebMvc
@Import({TestDatabaseConfiguration.class})
public class TestWebConfiguration implements WebMvcConfigurer, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

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
     * Local validator factory bean local validator factory bean.
     *
     * @return the local validator factory bean
     */
    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }

}
