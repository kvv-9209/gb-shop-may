package ru.gb.gbshopmay.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.gb.gbshopmay.config.ShopConfig;
import ru.gb.gbshopmay.entity.Manufacturer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(ShopConfig.class)
class ManufacturerDaoDataJpaTest {

    public static final String APPLE_COMPANY_NAME = "Apple";
    public static final String MICROSOFT_COMPANY_NAME = "Microsoft";

    @Autowired
    ManufacturerDao manufacturerDao;

    @Test
    public void saveTest() {
        Manufacturer manufacturer = Manufacturer.builder()
                .name(APPLE_COMPANY_NAME)
                .build();

        Manufacturer savedManufacturer = manufacturerDao.save(manufacturer);

        assertAll(
                () -> assertEquals(1L, savedManufacturer.getId()),
                () -> assertEquals(APPLE_COMPANY_NAME, savedManufacturer.getName()),
                () -> assertEquals(0, savedManufacturer.getVersion()),
                () -> assertEquals("User", savedManufacturer.getCreatedBy()),
                () -> assertNotNull(savedManufacturer.getCreatedDate()),
                () -> assertEquals("User", savedManufacturer.getLastModifiedBy()),
                () -> assertNotNull(savedManufacturer.getLastModifiedDate())

        );
    }

    @Test
    public void findAllTest() {
        Manufacturer manufacturer1 = Manufacturer.builder()
                .name(APPLE_COMPANY_NAME)
                .build();
        Manufacturer manufacturer2 = Manufacturer.builder()
                .name(MICROSOFT_COMPANY_NAME)
                .build();

        Manufacturer savedManufacturer1 = manufacturerDao.save(manufacturer1);
        Manufacturer savedManufacturer2 = manufacturerDao.save(manufacturer2);
        List<Manufacturer> manufacturerDaoAll = manufacturerDao.findAll();

        assertAll(
                () -> assertEquals(2, manufacturerDaoAll.size()),
                () -> assertEquals(1L, manufacturerDaoAll.get(0).getId()),
                () -> assertEquals(APPLE_COMPANY_NAME, manufacturerDaoAll.get(0).getName()),
                () -> assertEquals(2L, manufacturerDaoAll.get(1).getId()),
                () -> assertEquals(MICROSOFT_COMPANY_NAME, manufacturerDaoAll.get(1).getName()),
                () -> assertEquals(0, manufacturerDaoAll.get(0).getVersion()),
                () -> assertEquals("User", manufacturerDaoAll.get(0).getCreatedBy()),
                () -> assertNotNull(manufacturerDaoAll.get(0).getCreatedDate()),
                () -> assertEquals("User", manufacturerDaoAll.get(0).getLastModifiedBy()),
                () -> assertNotNull(manufacturerDaoAll.get(0).getLastModifiedDate())
        );
    }

    @Test
    public void deleteTest() {
        Manufacturer manufacturer = Manufacturer.builder()
                .name(APPLE_COMPANY_NAME)
                .build();
        Manufacturer savedManufacturer = manufacturerDao.save(manufacturer);

        assertEquals(1, manufacturerDao.findAll().size());

        manufacturerDao.deleteById(1L);

        assertEquals(0, manufacturerDao.findAll().size());
    }

    @Test
    public void updateTest() {

        Manufacturer manufacturer1 = Manufacturer.builder()
                .id(1L)
                .name(APPLE_COMPANY_NAME)
                .build();
        Manufacturer manufacturer2 = Manufacturer.builder()
                .id(1L)
                .name(MICROSOFT_COMPANY_NAME)
                .build();

        Manufacturer savedManufacturer1 = manufacturerDao.save(manufacturer1);
        assertAll(
                () -> assertEquals(1L, savedManufacturer1.getId()),
                () -> assertEquals(APPLE_COMPANY_NAME, savedManufacturer1.getName())
        );

        Manufacturer savedManufacturer2 = manufacturerDao.save(manufacturer2);

        assertAll(
                () -> assertEquals(1L, savedManufacturer2.getId()),
                () -> assertEquals(MICROSOFT_COMPANY_NAME, savedManufacturer2.getName())
        );
    }
}