package by.home.eventOrganizer.service.impl.human;

import by.home.eventOrganizer.repository.human.PersonRepository;
import by.home.eventOrganizer.service.human.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public abstract class PersonServiceImpl<T, ID> implements PersonService<T, ID> {

    @Autowired
    PersonRepository personRepository;

    @Override
    public List<T> findByAddress_Street(String name) {
        return personRepository.findByAddress_Street(name);
    }

    @Override
    public List<T> findByPhoneNumber(Long number) {
        return personRepository.findByPhoneNumber(number);
    }

    @Override
    public List<T> findByNameOrSurname(String name, String surname) {
        return personRepository.findByNameOrSurname(name , surname);
    }
}
