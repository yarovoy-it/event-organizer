package by.home.eventOrganizer.service.human;

import java.util.List;


public interface PersonService<T, ID> {

    List<T> findByAddress_Street(String name);

    List<T> findByPhoneNumber(Long number);

    List<T> findByNameOrSurname(String name, String surname);

}
