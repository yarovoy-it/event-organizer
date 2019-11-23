package by.home.eventOrganizer.repository.human;

import by.home.eventOrganizer.model.human.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Person repository.
 *
 * @param <T>  the type parameter have to be child from person
 * @param <ID> the type parameter
 */

public interface PersonRepository<T extends Person, ID> extends JpaRepository<T, ID> {

    /**
     * Find abstract person by name or surname list.
     *
     * @param name    the name
     * @param surname the surname
     * @return the list
     */
    List<T> findByNameOrSurname(String name, String surname);

    /**
     * Find abstract person by address street list.
     *
     * @param name the name of street
     * @return the list
     */
    List<T> findByAddress_Street(String name);

    /**
     * Find abstract person by phone number list.
     *
     * @param number the phone number of
     *               person
     * @return the list
     */
    List<T> findByPhoneNumber(Long number);

}
