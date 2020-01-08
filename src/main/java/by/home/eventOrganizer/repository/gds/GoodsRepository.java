package by.home.eventOrganizer.repository.gds;

import by.home.eventOrganizer.model.gds.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Goods repository.
 */
public interface GoodsRepository extends JpaRepository<Goods, Long> {

    /**
     * Find by name list.
     *
     * @param name the name
     * @return the list
     */
    List<Goods> findByName(String name);
}
