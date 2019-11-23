package by.home.eventOrganizer.repository.gds;

import by.home.eventOrganizer.model.gds.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<Goods, Long> {
}
