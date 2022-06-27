package ru.gb.gbshopmay.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.gbapimay.manufacturer.dto.ManufacturerDto;
import ru.gb.gbshopmay.dao.ManufacturerDao;
import ru.gb.gbshopmay.dao.ManufacturerDao;
import ru.gb.gbshopmay.entity.Manufacturer;
import ru.gb.gbshopmay.web.dto.mapper.ManufacturerMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ManufacturerService {
    private final ManufacturerDao manufacturerDao;
    private final ManufacturerMapper manufacturerMapper;
    

    public ManufacturerDto save(ManufacturerDto manufacturerDto) {
        Manufacturer manufacturer = manufacturerMapper.toManufacturer(manufacturerDto);
        if (manufacturer.getId() != null) {
            manufacturerDao.findById(manufacturerDto.getId()).ifPresent(
                    (p) -> manufacturer.setVersion(p.getVersion())
            );
        }
        return manufacturerMapper.toManufacturerDto(manufacturerDao.save(manufacturer));
    }


    @Transactional(readOnly = true)
    public ManufacturerDto findById(Long id) {
        return manufacturerMapper.toManufacturerDto(manufacturerDao.findById(id).orElse(null));
    }

    public List<ManufacturerDto> findAll() {
        return manufacturerDao.findAll().stream()
                .map(manufacturerMapper::toManufacturerDto)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        try {
            manufacturerDao.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
        }
    }

}
