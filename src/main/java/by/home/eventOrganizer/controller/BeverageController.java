package by.home.eventOrganizer.controller;

import by.home.eventOrganizer.model.gds.Beverage;
import by.home.eventOrganizer.service.gds.BeverageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/beverages")
public class BeverageController {

    private final BeverageService beverageService;

    public BeverageController(BeverageService beverageService) {
        this.beverageService = beverageService;

    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Beverage>> getAll() {
        final List<Beverage> beverages = beverageService.findAll();
        return new ResponseEntity<>(beverages, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Beverage> getOne(@PathVariable Long id) {
        final Beverage beverage = beverageService.findById(id);
        return new ResponseEntity<>(beverage, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Beverage> save(@RequestBody Beverage beverage) {
        beverage.setId(null);
        Beverage transientBeverage = beverageService.save(beverage);
        return new ResponseEntity<>(transientBeverage, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Beverage> update(@RequestBody Beverage beverage, @PathVariable Long id) {
        if (!Objects.equals(id, beverage.getId())) {
            throw new RuntimeException("controller.beverage.unexpectedId");
        }
        final Beverage transientBeverage = beverageService.update(beverage);
        return new ResponseEntity<>(transientBeverage, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        beverageService.deleteById(id);
    }

}
