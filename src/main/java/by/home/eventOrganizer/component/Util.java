package by.home.eventOrganizer.component;

import by.home.eventOrganizer.model.human.Person;
import by.home.eventOrganizer.repository.human.PersonRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Util {

    private final LocalizedMessageSource localizedMessageSource;

    private final PersonRepository personRepository;

    public Util(LocalizedMessageSource localizedMessageSource, PersonRepository personRepository) {
        this.localizedMessageSource = localizedMessageSource;
        this.personRepository = personRepository;
    }

    public Boolean uniqueNumber(Person person) {
        List<Person> customers = personRepository.findByPhoneNumber(person.getPhoneNumber());
        Boolean notUniquePhone = customers.stream()
                .anyMatch(st -> st.getPhoneNumber().equals(person.getPhoneNumber()));
        return notUniquePhone;
    }

    public void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(localizedMessageSource.getMessage(errorMessage, new Object[]{}));
        }
    }
}
