package by.home.eventOrganizer.service.detail;

import by.home.eventOrganizer.model.detail.Address;

import java.util.List;
import java.util.Optional;


/**
 * The interface Address service.
 */
public interface AddressService {

    /**
     * Save address.
     *
     * @param address the address
     * @return the address
     */
    Address save(Address address);

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     */
    Optional<Address> findById(Long id);

    /**
     * Find all list.
     *
     * @return the list
     */
    List<Address> findAll();

    /**
     * Update address.
     *
     * @param address the address
     * @return the address
     */
    Address update(Address address);

    /**
     * Delete.
     *
     * @param address the address
     */
    void delete(Address address);

    /**
     * Delete by id.
     *
     * @param id the id
     */
    void deleteById(Long id);
}
