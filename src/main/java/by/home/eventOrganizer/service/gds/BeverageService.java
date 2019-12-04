package by.home.eventOrganizer.service.gds;

import by.home.eventOrganizer.model.gds.Beverage;

import java.util.List;

public interface BeverageService {

    List<Beverage> findAll();

    Beverage findById(Long id);

    Beverage save(Beverage role);

    Beverage update(Beverage role);

    void delete(Beverage role);

    void deleteById(Long id);
}
