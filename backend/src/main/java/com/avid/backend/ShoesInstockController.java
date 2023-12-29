package com.avid.backend;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/instock")
public class ShoesInstockController {
    private final ShoesRepository shoesRepository;

    public ShoesInstockController(ShoesRepository shoesRepository) {
        this.shoesRepository = shoesRepository;
    }

    @GetMapping
    private List<ShoesInstockDto> list() {
        return shoesRepository.findAll(Sort.by("name", "size")).stream().map(ShoesInstockDto::from).toList();
    }

    @PostMapping
    private String create(ShoesInstockDto dto) {
        Optional<ShoesInstockEntity> oldEntity = shoesRepository.findByNameAndSize(dto.getName(), dto.getSize());
        if (oldEntity.isEmpty()) {
            ShoesInstockEntity entity = dto.toEntity();
            entity = shoesRepository.save(entity);
            return entity.getId().toString();
        } else {
            return oldEntity.get().getId().toString();
        }
    }

    @PutMapping("/{id}")
    private void update(@PathVariable String id, @RequestBody ShoesInstockDto body) {
        Optional<ShoesInstockEntity> oldEntity = shoesRepository.findById(UUID.fromString(id));
        if (oldEntity.isPresent()) {
            ShoesInstockEntity newEntity = body.toEntity();
            newEntity.setId(UUID.fromString(id));
            shoesRepository.save(newEntity);
        }
    }

    @PutMapping
    private void update(@RequestBody List<ShoesInstockDto> dtoList) {
        shoesRepository.deleteAll();
        shoesRepository.saveAll(dtoList.stream().map(ShoesInstockDto::toEntity).toList());
    }

    @DeleteMapping("/{id}")
    private void delete(@PathVariable String id) {
        shoesRepository.deleteById(UUID.fromString(id));
    }
}
