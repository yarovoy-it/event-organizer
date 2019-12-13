package by.home.eventOrganizer.controller;

import by.home.eventOrganizer.dto.gds.BeverageDto;
import by.home.eventOrganizer.model.gds.Beverage;
import by.home.eventOrganizer.service.gds.BeverageService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/beverages")
public class BeverageController {

    private final Mapper mapper;

    private final BeverageService beverageService;

    public BeverageController(Mapper mapper, BeverageService beverageService) {
        this.mapper = mapper;
        this.beverageService = beverageService;

    }

    @GetMapping
    public ResponseEntity<List<BeverageDto>> getAll() {
        final List<Beverage> beverages = beverageService.findAll();
        final List<BeverageDto> beverageDtoList = beverages.stream()
                .map((beverage)-> mapper.map(beverage, BeverageDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(beverageDtoList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BeverageDto> getOne(@PathVariable Long id) {
        final BeverageDto beverageDto = mapper.map(beverageService.findById(id), BeverageDto.class);
        return new ResponseEntity<>(beverageDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BeverageDto> save(@Valid @RequestBody BeverageDto beverageDto) {
        beverageDto.setId(null);
        final BeverageDto responseBeverageDto = mapper.map(beverageService.save(mapper.map(beverageDto, Beverage.class)), BeverageDto.class);
        return new ResponseEntity<>(responseBeverageDto, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BeverageDto> update(@Valid @RequestBody BeverageDto beverageDto, @PathVariable Long id) {
        if (!Objects.equals(id, beverageDto.getId())) {
            throw new RuntimeException("controller.beverage.unexpectedId");
        }
        final BeverageDto responseBeverageDto = mapper.map(beverageService.update(mapper.map(beverageDto, Beverage.class)), BeverageDto.class);
        return new ResponseEntity<>(responseBeverageDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        beverageService.deleteById(id);
    }

}
