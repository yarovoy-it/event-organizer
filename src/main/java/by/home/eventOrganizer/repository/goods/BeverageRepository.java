package by.home.eventOrganizer.repository.goods;

import by.home.eventOrganizer.model.goods.Beverage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Beverage repository.
 */
public interface BeverageRepository extends JpaRepository<Beverage, Long> {

    /**
     * Find by name list.
     *
     * @param name the name
     * @return the list
     */
    List<Beverage> findByName(String name);


}
