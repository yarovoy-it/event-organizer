package by.home.eventOrganizer.repository.goods;

import by.home.eventOrganizer.model.goods.Goods;
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
