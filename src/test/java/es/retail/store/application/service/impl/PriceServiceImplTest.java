package es.retail.store.application.service.impl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import es.retail.store.application.storage.port.PriceRepository;
import es.retail.store.domain.model.Prices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class PriceServiceImplTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceServiceImpl priceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenFindApplicablePriceIsCalled_thenRepositoryIsInvoked() {
        // Given
        LocalDateTime applicationDate = LocalDateTime.now();
        Long productId = 1L;
        Long brandId = 1L;
        List<Prices> expectedPrices = List.of(new Prices(1, productId, 2, applicationDate, applicationDate.plusHours(1), null));

        when(priceRepository.findApplicablePrice(applicationDate, productId, brandId)).thenReturn(expectedPrices);

        // When
        List<Prices> result = priceService.findApplicablePrice(applicationDate, productId, brandId);

        // Then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(expectedPrices, result);

        verify(priceRepository, times(1)).findApplicablePrice(applicationDate, productId, brandId);
    }

    @Test
    void whenFindApplicablePriceReturnsNull_thenEmptyListIsReturned() {
        // Given
        LocalDateTime applicationDate = LocalDateTime.now();
        Long productId = 1L;
        Long brandId = 1L;

        when(priceRepository.findApplicablePrice(applicationDate, productId, brandId)).thenReturn(null);

        // When
        List<Prices> result = priceService.findApplicablePrice(applicationDate, productId, brandId);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(priceRepository, times(1)).findApplicablePrice(applicationDate, productId, brandId);
    }
}
