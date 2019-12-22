package by.home.eventOrganizer.service.impl.gds;

import by.home.eventOrganizer.model.gds.Beverage;
import by.home.eventOrganizer.repository.gds.BeverageRepository;
import by.home.eventOrganizer.service.gds.BeverageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BeverageServiceImpl implements BeverageService {

    @Autowired
    private BeverageRepository beverageRepository;

    @Override
    public List<Beverage> findByName(String name) {
        return beverageRepository.findByName(name);
    }

    @Override
    public List<Beverage> findAll() {
        return beverageRepository.findAll();
    }

    @Override
    public Beverage findById(Long id) {
        return beverageRepository.findById(id).orElseThrow(() -> new RuntimeException("error.beverage.notExist"));
    }

    @Override
    public Beverage save(Beverage beverage) {
        return beverageRepository.saveAndFlush(beverage);
    }

    @Override
    public Beverage update(Beverage beverage) {
        return beverageRepository.saveAndFlush(beverage);
    }

    @Override
    public Beverage getByIdWithCount(Long id, Integer count) {
        Beverage beverage = beverageRepository.findById(id).orElseThrow(() -> new RuntimeException("error.beverage.notExist"));
        if (count != null) {
            beverage.setCount(count);
            return beverageRepository.saveAndFlush(beverage);
        } else {
            throw new RuntimeException("We can`t set null on " + beverage.getName());
        }
    }

    @Override
    public void delete(Beverage beverage) {
        beverageRepository.delete(beverage);
    }

    @Override
    public void deleteById(Long id) {
        beverageRepository.deleteById(id);
    }
}
