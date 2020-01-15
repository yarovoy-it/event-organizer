package by.home.eventOrganizer.service.goods.impl;

import by.home.eventOrganizer.component.LocalizedMessageSource;
import by.home.eventOrganizer.model.goods.Goods;
import by.home.eventOrganizer.repository.goods.GoodsRepository;
import by.home.eventOrganizer.service.goods.GoodsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {

    private final LocalizedMessageSource localizedMessageSource;

    private final GoodsRepository goodsRepository;

    public GoodsServiceImpl(LocalizedMessageSource localizedMessageSource, GoodsRepository goodsRepository) {
        this.localizedMessageSource = localizedMessageSource;
        this.goodsRepository = goodsRepository;
    }

    @Override
    public List<Goods> findAll() {
        return goodsRepository.findAll();
    }

    @Override
    public Goods findById(Long id) {
        return goodsRepository.findById(id).orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.goods.notExist", new Object[]{})));
    }


    @Override
    public List<Goods> findByName(String name) {
        validate(name == null, "error.goods.name.null");
        return goodsRepository.findByName(name);
    }

    @Override
    public Goods save(Goods goods) {
        validate(goods == null, "error.goods.null");
        return goodsRepository.saveAndFlush(goods);
    }

    @Override
    public Goods update(Goods goods) {
        final Long id = goods.getId();
        validate(id == null, "error.goods.haveId");
        findById(id);
        return goodsRepository.saveAndFlush(goods);
    }

    @Override
    public void delete(Goods goods) {
        validate(goods == null, "error.goods.null");
        goodsRepository.delete(goods);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        goodsRepository.deleteById(id);
    }

    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(localizedMessageSource.getMessage(errorMessage, new Object[]{}));
        }
    }
}
