package es.retail.store.infrastructure.api.rest;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import es.retail.store.application.service.port.PriceService;
import es.retail.store.domain.model.Prices;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class PriceControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PriceService priceService;

    @InjectMocks
    private PriceController priceController;

    @BeforeEach
    public void setup() {
        mockMvc = standaloneSetup(priceController).build();
    }

    @Test
    public void getPrice_ReturnsPricesList() throws Exception {
    	LocalDateTime startDate = LocalDateTime.now();
    	LocalDateTime endDate = LocalDateTime.now();
        Long productId = 1L;
        Long brandId = 1L;

        Prices price = new Prices(brandId.intValue(), productId, 1, startDate, endDate, null);
        List<Prices> pricesList = Arrays.asList(price);

        when(priceService.findApplicablePrice(startDate, productId, brandId)).thenReturn(pricesList);

        mockMvc.perform(get("/api/prices")
                .param("applicationDate", startDate.toString())
                .param("productId", productId.toString())
                .param("brandId", brandId.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(priceService).findApplicablePrice(startDate, productId, brandId);
    }

    @Test
    public void getPrice_WhenNotFound_Returns404() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.now();
        Long productId = 2L;
        Long brandId = 2L;

        when(priceService.findApplicablePrice(applicationDate, productId, brandId)).thenThrow(NoSuchElementException.class);

        mockMvc.perform(get("/api/prices")
                .param("applicationDate", applicationDate.toString())
                .param("productId", productId.toString())
                .param("brandId", brandId.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(priceService).findApplicablePrice(applicationDate, productId, brandId);
    }
}
