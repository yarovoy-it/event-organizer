package by.home.eventOrganizer.service.goods;

import by.home.eventOrganizer.model.goods.Goods;

import java.util.List;

/**
 * The interface Goods service.
 */
public interface GoodsService {

    /**
     * Find by name list.
     *
     * @param name the name
     * @return the list
     */
    List<Goods> findByName(String name);

    /**
     * Find all list.
     *
     * @return the list
     */
    List<Goods> findAll();

    /**
     * Find by id goods.
     *
     * @param id the id
     * @return the goods
     */
    Goods findById(Long id);

    /**
     * Save goods.
     *
     * @param goods the goods
     * @return the goods
     */
    Goods save(Goods goods);

    /**
     * Update goods.
     *
     * @param goods the goods
     * @return the goods
     */
    Goods update(Goods goods);

    /**
     * Delete.
     *
     * @param goods the goods
     */
    void delete(Goods goods);

    /**
     * Delete by id.
     *
     * @param id the id
     */
    void deleteById(Long id);
}
