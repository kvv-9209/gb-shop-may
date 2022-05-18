package ru.gb.gbshopmay.web.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.gb.gbshopmay.entity.Manufacturer;
import ru.gb.gbshopmay.web.dto.ManufacturerDto;

@Mapper
public interface ManufacturerMapper {
    @Mapping(source = "manufacturerId", target = "id")
    Manufacturer toManufacturer(ManufacturerDto manufacturerDto);

    @Mapping(source = "id", target = "manufacturerId")
    ManufacturerDto toManufacturerDto(Manufacturer manufacturer);
}
