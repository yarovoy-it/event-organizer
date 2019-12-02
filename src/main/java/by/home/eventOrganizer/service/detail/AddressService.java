package by.home.eventOrganizer.service.detail;

import by.home.eventOrganizer.model.detail.Address;

import java.util.List;
import java.util.Optional;


public interface AddressService {

    Address save(Address address);

    Optional<Address> findById(Long id);

    List<Address> findAll();
}
