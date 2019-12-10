package by.home.eventOrganizer.repository.gds;

import by.home.eventOrganizer.model.gds.Beverage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BeverageRepository extends JpaRepository<Beverage, Long> {

    List<Beverage> findByName(String name);


}
