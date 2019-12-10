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

@RestController
@RequestMapping("/address")
public class AddressController {

    private final Mapper mapper;

    private final AddressService addressService;

    public AddressController(Mapper mapper, AddressService addressService) {
        this.mapper = mapper;
        this.addressService = addressService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<AddressDto>> getAll(){
        final List<Address> addresses = addressService.findAll();
        final List<AddressDto> addressDtoList = addresses.stream()
                .map((address)-> mapper.map(address, AddressDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(addressDtoList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<AddressDto> getOne(@PathVariable Long id) {
        final AddressDto addressDto = mapper.map(addressService.findById(id), AddressDto.class);
        return new ResponseEntity<>(addressDto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AddressDto> save(@Valid @RequestBody AddressDto addressDto) {
        addressDto.setId(null);
        final AddressDto responseAddressDto = mapper.map(addressService.save(mapper.map(addressDto, Address.class)), AddressDto.class);
        return new ResponseEntity<>(responseAddressDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<AddressDto> update(@Valid @RequestBody AddressDto addressDto, @PathVariable Long id) {
        if (!Objects.equals(id, addressDto.getId())) {
            throw new RuntimeException("controller.address.unexpectedId");
        }
        final AddressDto responseAddressDto = mapper.map(addressService.update(mapper.map(addressDto, Address.class)), AddressDto.class);
        return new ResponseEntity<>(responseAddressDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        addressService.deleteById(id);
    }


}
