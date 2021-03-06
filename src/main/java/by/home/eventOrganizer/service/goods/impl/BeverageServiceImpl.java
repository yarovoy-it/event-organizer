package by.home.eventOrganizer.service.goods.impl;

import by.home.eventOrganizer.component.LocalizedMessageSource;
import by.home.eventOrganizer.model.goods.Beverage;
import by.home.eventOrganizer.repository.goods.BeverageRepository;
import by.home.eventOrganizer.service.goods.BeverageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The type Beverage service.
 */
@Service
@Transactional
public class BeverageServiceImpl implements BeverageService {

    private final LocalizedMessageSource localizedMessageSource;

    private final BeverageRepository beverageRepository;

    /**
     * Instantiates a new Beverage service.
     *
     * @param localizedMessageSource the localized message source
     * @param beverageRepository     the beverage repository
     */
    public BeverageServiceImpl(LocalizedMessageSource localizedMessageSource, BeverageRepository beverageRepository) {
        this.localizedMessageSource = localizedMessageSource;
        this.beverageRepository = beverageRepository;
    }

    @Override
    public List<Beverage> findByName(String name) {
        validate(name == null, "error.beverage.name.null");
        return beverageRepository.findByName(name);
    }

    @Override
    public List<Beverage> findAll() {
        return beverageRepository.findAll();
    }

    @Override
    public Beverage findById(Long id) {
        return beverageRepository.findById(id).orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.beverage.notExist", new Object[]{})));
    }

    @Override
    public Beverage save(Beverage beverage) {
        validate(beverage == null, "error.beverage.null");
        return beverageRepository.saveAndFlush(beverage);
    }

    @Override
    public Beverage update(Beverage beverage) {
        final Long id = beverage.getId();
        validate(id == null, "error.beverage.haveId");
        findById(id);
        return beverageRepository.saveAndFlush(beverage);
    }

    @Override
    public void delete(Beverage beverage) {
        validate(beverage == null, "error.beverage.null");
        beverageRepository.delete(beverage);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        beverageRepository.deleteById(id);
    }

    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(localizedMessageSource.getMessage(errorMessage, new Object[]{}));
        }
    }
}
