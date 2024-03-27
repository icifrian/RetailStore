package es.retail.store.infrastructure.api.rest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.retail.store.application.service.port.PriceService;
import es.retail.store.domain.model.Prices;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

    private final PriceService priceService;
    
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping
    @CircuitBreaker(name = "price-service")
    public ResponseEntity<?> getPrice(@RequestParam("applicationDate") LocalDateTime applicationDate,
                                                      @RequestParam("productId") Long productId,
                                                      @RequestParam("brandId") Long brandId) {
    	try {
	    	List<Prices> listPrice = priceService.findApplicablePrice(applicationDate, productId, brandId);
	        return ResponseEntity.ok(listPrice);
    	} catch (NoSuchElementException e) {
    	      return ResponseEntity.notFound().build();
    	}
    }

}
