package ru.gb.gbshopmay.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.gb.gbshopmay.config.ShopConfig;
import ru.gb.gbshopmay.entity.Manufacturer;
import ru.gb.gbshopmay.web.dto.ManufacturerDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DataJpaTest
@Import(ShopConfig.class)
class ManufacturerDaoDataJpaTest {

    public static final String APPLE_COMPANY_NAME = "Apple";
    public static final String MICROSOFT_COMPANY_NAME = "Microsoft";

    @Autowired
    ManufacturerDao manufacturerDao;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void saveTest() {
        Manufacturer manufacturer = Manufacturer.builder()
                .id(1L)
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
        entityManager.persist(Manufacturer.builder()
                .name(APPLE_COMPANY_NAME)
                .build());
        entityManager.persist(Manufacturer.builder()
                .name(MICROSOFT_COMPANY_NAME)
                .build());

        List<Manufacturer> manufacturerList = manufacturerDao.findAll();
        assertAll(
                () -> assertEquals(1L, manufacturerList.get(0).getId()),
                () -> assertEquals(APPLE_COMPANY_NAME,  manufacturerList.get(0).getName()),
                () -> assertEquals(2L, manufacturerList.get(1).getId()),
                () -> assertEquals(MICROSOFT_COMPANY_NAME,  manufacturerList.get(1).getName())
        );
    }

    @Test
    public void deleteTest() {

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
                .id(2L)
                .name(MICROSOFT_COMPANY_NAME)
                .build();

        Manufacturer saveManufacturer1 = manufacturerDao.save(manufacturer1);
        assertAll(
                () -> assertEquals(1L, saveManufacturer1.getId()),
                () -> assertEquals(APPLE_COMPANY_NAME, saveManufacturer1.getName())
        );

        Manufacturer saveManufacturer2 = manufacturerDao.save(manufacturer2);

        assertAll(
                () -> assertEquals(1L, saveManufacturer2.getId()),
                () -> assertEquals(MICROSOFT_COMPANY_NAME, saveManufacturer2.getName())
        );
    }
}