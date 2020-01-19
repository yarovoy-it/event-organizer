package by.home.eventOrganizer.controller;

import by.home.eventOrganizer.dto.goods.GoodsDto;
import by.home.eventOrganizer.model.goods.Goods;
import by.home.eventOrganizer.service.goods.GoodsService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The type Goods controller.
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    private final Mapper mapper;

    private final GoodsService goodsService;

    /**
     * Instantiates a new Goods controller.
     *
     * @param mapper       the mapper
     * @param goodsService the goods service
     */
    public GoodsController(Mapper mapper, GoodsService goodsService) {
        this.mapper = mapper;
        this.goodsService = goodsService;
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    @GetMapping
    public ResponseEntity<List<GoodsDto>> getAll() {
        final List<Goods> goodsAll = goodsService.findAll();
        final List<GoodsDto> goodsDtoList = goodsAll.stream()
                .map((goods) -> mapper.map(goods, GoodsDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(goodsDtoList, HttpStatus.OK);
    }

    /**
     * Gets one.
     *
     * @param id the id
     * @return the one
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<GoodsDto> getOne(@PathVariable Long id) {
        final GoodsDto beverageDto = mapper.map(goodsService.findById(id), GoodsDto.class);
        return new ResponseEntity<>(beverageDto, HttpStatus.OK);
    }

    /**
     * Save response entity.
     *
     * @param goodsDto the goods dto
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity<GoodsDto> save(@Valid @RequestBody GoodsDto goodsDto) {
        goodsDto.setId(null);
        final GoodsDto responseGoodsDto = mapper.map(goodsService.save(mapper.map(goodsDto, Goods.class)), GoodsDto.class);
        return new ResponseEntity<>(responseGoodsDto, HttpStatus.OK);
    }

    /**
     * Update response entity.
     *
     * @param goodsDto the goods dto
     * @param id       the id
     * @return the response entity
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<GoodsDto> update(@Valid @RequestBody GoodsDto goodsDto, @PathVariable Long id) {
        if (!Objects.equals(id, goodsDto.getId())) {
            throw new RuntimeException("controller.goods.unexpectedId");
        }
        final GoodsDto responseGoodsDto = mapper.map(goodsService.update(mapper.map(goodsDto, Goods.class)), GoodsDto.class);
        return new ResponseEntity<>(responseGoodsDto, HttpStatus.OK);
    }

    /**
     * Delete.
     *
     * @param id the id
     */
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        goodsService.deleteById(id);
    }
}
