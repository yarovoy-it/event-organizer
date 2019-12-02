package by.home.eventOrganizer.service.impl.detail;

import by.home.eventOrganizer.model.detail.Address;
import by.home.eventOrganizer.repository.detail.AddressRepository;
import by.home.eventOrganizer.service.detail.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address save(Address address) {
        return addressRepository.saveAndFlush(address);
    }

    @Override
    public Optional<Address> findById(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }
}
