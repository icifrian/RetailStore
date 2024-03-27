package es.retail.store.infrastructure.storage.db.spring_data.adapter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import es.retail.store.domain.model.Prices;
import es.retail.store.infrastructure.storage.db.spring_data.jpa.JpaPriceRepository;
import es.retail.store.infrastructure.storage.db.spring_data.mapper.PricesDboMapper;
import es.retail.store.infrastructure.storage.db.spring_data.model.PricesDbo;

@ExtendWith(MockitoExtension.class)
public class PriceRepositoryAdapterTest {

    @Mock
    private JpaPriceRepository jpaPriceRepository;

    @Mock
    private PricesDboMapper pricesDboMapper;

    @InjectMocks
    private PriceRepositoryAdapter priceRepositoryAdapter;

    @Test
    public void findApplicablePrice_WhenFound_ShouldReturnPrices() {
        LocalDateTime startDate = LocalDateTime.now();
    	LocalDateTime endDate = LocalDateTime.now();
        Long productId = 1L;
        Long brandId = 1L;

        PricesDbo pricesDbo = new PricesDbo(); 
        List<PricesDbo> dboList = Arrays.asList(pricesDbo);

        Prices price = new Prices(brandId.intValue(), productId, 1, startDate, endDate, null);

        when(jpaPriceRepository.findApplicablePrices(startDate, productId, brandId.intValue())).thenReturn(dboList);
        when(pricesDboMapper.toDomain(any(PricesDbo.class))).thenReturn(price);

        List<Prices> result = priceRepositoryAdapter.findApplicablePrice(startDate, productId, brandId);

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(jpaPriceRepository).findApplicablePrices(startDate, productId, brandId.intValue());
        verify(pricesDboMapper).toDomain(any(PricesDbo.class));
    }

    @Test
    public void findApplicablePrice_WhenNotFound_ShouldThrowException() {
        LocalDateTime applicationDate = LocalDateTime.now();
        Long productId = 2L;
        Long brandId = 2L;

        when(jpaPriceRepository.findApplicablePrices(applicationDate, productId, brandId.intValue())).thenReturn(Arrays.asList());

        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            priceRepositoryAdapter.findApplicablePrice(applicationDate, productId, brandId);
        });

        String expectedMessage = "Price not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        verify(jpaPriceRepository).findApplicablePrices(applicationDate, productId, brandId.intValue());
        verify(pricesDboMapper, never()).toDomain(any(PricesDbo.class));
    }
}
