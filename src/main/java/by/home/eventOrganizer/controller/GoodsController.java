package by.home.eventOrganizer.controller;

import by.home.eventOrganizer.dto.gds.GoodsDto;
import by.home.eventOrganizer.model.gds.Goods;
import by.home.eventOrganizer.service.gds.GoodsService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    private final Mapper mapper;

    private final GoodsService goodsService;

    public GoodsController(Mapper mapper, GoodsService goodsService) {
        this.mapper = mapper;
        this.goodsService = goodsService;
    }

    @GetMapping
    public ResponseEntity<List<GoodsDto>> getAll() {
        final List<Goods> goodsAll = goodsService.findAll();
        final List<GoodsDto> goodsDtoList = goodsAll.stream()
                .map((goods) -> mapper.map(goods, GoodsDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(goodsDtoList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GoodsDto> getOne(@PathVariable Long id) {
        final GoodsDto beverageDto = mapper.map(goodsService.findById(id), GoodsDto.class);
        return new ResponseEntity<>(beverageDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GoodsDto> save(@Valid @RequestBody GoodsDto goodsDto) {
        goodsDto.setId(null);
        final GoodsDto responseGoodsDto = mapper.map(goodsService.save(mapper.map(goodsDto, Goods.class)), GoodsDto.class);
        return new ResponseEntity<>(responseGoodsDto, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<GoodsDto> update(@Valid @RequestBody GoodsDto goodsDto, @PathVariable Long id) {
        if (!Objects.equals(id, goodsDto.getId())) {
            throw new RuntimeException("controller.goods.unexpectedId");
        }
        final GoodsDto responseGoodsDto = mapper.map(goodsService.update(mapper.map(goodsDto, Goods.class)), GoodsDto.class);
        return new ResponseEntity<>(responseGoodsDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        goodsService.deleteById(id);
    }
}
