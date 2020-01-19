package by.home.eventOrganizer.controller;


import by.home.eventOrganizer.dto.detail.AddressDto;
import by.home.eventOrganizer.model.detail.Address;
import by.home.eventOrganizer.service.detail.AddressService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The type Address controller.
 */
@RestController
@RequestMapping("/address")
public class AddressController {

    private final Mapper mapper;

    private final AddressService addressService;

    /**
     * Instantiates a new Address controller.
     *
     * @param mapper         the mapper
     * @param addressService the address service
     */
    public AddressController(Mapper mapper, AddressService addressService) {
        this.mapper = mapper;
        this.addressService = addressService;
    }

    /**
     * Get all response entity.
     *
     * @return the response entity
     */
    @GetMapping
    public ResponseEntity<List<AddressDto>> getAll(){
        final List<Address> addresses = addressService.findAll();
        final List<AddressDto> addressDtoList = addresses.stream()
                .map((address)-> mapper.map(address, AddressDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(addressDtoList, HttpStatus.OK);
    }

    /**
     * Gets one.
     *
     * @param id the id
     * @return the one
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<AddressDto> getOne(@PathVariable Long id) {
        final AddressDto addressDto = mapper.map(addressService.findById(id), AddressDto.class);
        return new ResponseEntity<>(addressDto, HttpStatus.OK);
    }

    /**
     * Save response entity.
     *
     * @param addressDto the address dto
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity<AddressDto> save(@Valid @RequestBody AddressDto addressDto) {
        addressDto.setId(null);
        final AddressDto responseAddressDto = mapper.map(addressService.save(mapper.map(addressDto, Address.class)), AddressDto.class);
        return new ResponseEntity<>(responseAddressDto, HttpStatus.OK);
    }

    /**
     * Update response entity.
     *
     * @param addressDto the address dto
     * @param id         the id
     * @return the response entity
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<AddressDto> update(@Valid @RequestBody AddressDto addressDto, @PathVariable Long id) {
        if (!Objects.equals(id, addressDto.getId())) {
            throw new RuntimeException("controller.address.unexpectedId");
        }
        final AddressDto responseAddressDto = mapper.map(addressService.update(mapper.map(addressDto, Address.class)), AddressDto.class);
        return new ResponseEntity<>(responseAddressDto, HttpStatus.OK);
    }

    /**
     * Delete.
     *
     * @param id the id
     */
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        addressService.deleteById(id);
    }


}
