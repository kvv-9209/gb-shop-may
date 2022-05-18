package ru.gb.gbshopmay.web.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.gb.gbshopmay.entity.Manufacturer;
import ru.gb.gbshopmay.web.dto.ManufacturerDto;

@Mapper
public interface ManufacturerMapper {
    Manufacturer toManufacturer(ManufacturerDto manufacturerDto);

    ManufacturerDto toManufacturerDto(Manufacturer manufacturer);
}
