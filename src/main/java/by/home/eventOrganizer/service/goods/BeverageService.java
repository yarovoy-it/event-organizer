package by.home.eventOrganizer.service.goods;

import by.home.eventOrganizer.model.goods.Beverage;

import java.util.List;

/**
 * The interface Beverage service.
 */
public interface BeverageService {

    /**
     * Find by name list.
     *
     * @param name the name
     * @return the list
     */
    List<Beverage> findByName(String name);

    /**
     * Find all list.
     *
     * @return the list
     */
    List<Beverage> findAll();

    /**
     * Find by id beverage.
     *
     * @param id the id
     * @return the beverage
     */
    Beverage findById(Long id);

    /**
     * Save beverage.
     *
     * @param beverage the beverage
     * @return the beverage
     */
    Beverage save(Beverage beverage);

    /**
     * Update beverage.
     *
     * @param beverage the beverage
     * @return the beverage
     */
    Beverage update(Beverage beverage);


    /**
     * Delete.
     *
     * @param beverage the beverage
     */
    void delete(Beverage beverage);

    /**
     * Delete by id.
     *
     * @param id the id
     */
    void deleteById(Long id);
}
