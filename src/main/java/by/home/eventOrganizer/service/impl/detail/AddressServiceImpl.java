package by.home.eventOrganizer.service.impl.detail;

import by.home.eventOrganizer.component.LocalizedMessageSource;
import by.home.eventOrganizer.model.detail.Address;
import by.home.eventOrganizer.repository.detail.AddressRepository;
import by.home.eventOrganizer.service.detail.AddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    private final LocalizedMessageSource localizedMessageSource;

    private final AddressRepository addressRepository;

    public AddressServiceImpl(LocalizedMessageSource localizedMessageSource, AddressRepository addressRepository) {
        this.localizedMessageSource = localizedMessageSource;
        this.addressRepository = addressRepository;
    }

    @Override
    public Address save(Address address) {
        address.setId(null);
        validate(address.getCity() == null || address.getStreet() == null || address.getHouseNumber() == null,
                "error.address.null");
        return addressRepository.saveAndFlush(address);
    }

    @Override
    public Address findById(Long id) {
        return addressRepository.findById(id).orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.address.notExist", new Object[]{})));
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address update(Address address) {
        validate(address.getId() == null, "error.address.haveId");
        findById(address.getId());
        return addressRepository.saveAndFlush(address);
    }

    @Override
    public void delete(Address address) {
        validate(address == null, "error.address.null");
        addressRepository.delete(address);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        addressRepository.deleteById(id);
    }

    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(localizedMessageSource.getMessage(errorMessage, new Object[]{}));
        }
    }
}
