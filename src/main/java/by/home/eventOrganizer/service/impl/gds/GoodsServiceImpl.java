package by.home.eventOrganizer.service.impl.gds;

import by.home.eventOrganizer.model.gds.Goods;
import by.home.eventOrganizer.repository.gds.GoodsRepository;
import by.home.eventOrganizer.service.gds.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public List<Goods> findAll() {
        return goodsRepository.findAll();
    }

    @Override
    public Goods findById(Long id) {
        return goodsRepository.findById(id).orElseThrow(()-> new RuntimeException("error"));
    }

    @Override
    public Goods save(Goods goods) {
        return goodsRepository.save(goods);
    }

    @Override
    public Goods update(Goods goods) {
        return goodsRepository.saveAndFlush(goods);
    }

    @Override
    public void delete(Goods goods) {
        goodsRepository.delete(goods);
    }

    @Override
    public void deleteById(Long id) {
        goodsRepository.deleteById(id);
    }
}
