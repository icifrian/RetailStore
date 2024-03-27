package es.retail.store.infrastructure.storage.db.spring_data.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import es.retail.store.domain.model.Prices;
import es.retail.store.infrastructure.storage.db.spring_data.model.PricesDbo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PricesDboMapperTest {

    private PricesDboMapper pricesDboMapper = Mappers.getMapper(PricesDboMapper.class);

    @Test
    public void whenPricesDboIsNull_thenReturnsNull() {
        assertNull(pricesDboMapper.toDomain(null));
    }

    @Test
    public void whenPricesDboIsNotNull_thenMapsCorrectly() {
        PricesDbo pricesDbo = new PricesDbo();
        pricesDbo.setId(1L);
        pricesDbo.setBrandId(100);
        pricesDbo.setStartDate(LocalDateTime.of(2024, 3, 20, 0, 0));
        pricesDbo.setEndDate(LocalDateTime.of(2024, 3, 30, 23, 59));
        pricesDbo.setPriceList(1);
        pricesDbo.setProductId(1L);
        pricesDbo.setPriority(1);
        pricesDbo.setPrice(new BigDecimal("29.95"));
        pricesDbo.setCurr("EUR");

        Prices result = pricesDboMapper.toDomain(pricesDbo);

        assertEquals(pricesDbo.getBrandId(), result.brandId());
        assertEquals(pricesDbo.getProductId(), result.productId());
        assertEquals(pricesDbo.getPriceList(), result.priceList());
        assertEquals(pricesDbo.getStartDate(), result.startDate());
        assertEquals(pricesDbo.getEndDate(), result.endDate());
        assertEquals(pricesDbo.getPrice(), result.price());
    }
}
