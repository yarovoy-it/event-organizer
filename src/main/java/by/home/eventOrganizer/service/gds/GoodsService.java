package by.home.eventOrganizer.service.gds;

import by.home.eventOrganizer.model.gds.Goods;

import java.util.List;

/**
 * The interface Goods service.
 */
public interface GoodsService {

    Goods getByIdWithCount(Long id, Integer count);

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
