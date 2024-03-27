package es.retail.store.application.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import es.retail.store.application.service.port.PriceService;
import es.retail.store.application.storage.port.PriceRepository;
import es.retail.store.domain.model.Prices;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class PriceServiceImpl implements PriceService {

	private final PriceRepository priceRepository;

    @Override
    public List<Prices> findApplicablePrice(LocalDateTime applicationDate, Long productId, Long brandId) {
    	
    	log.info("Starting findApplicablePrice by applicationDate {}, productId {} and brandId {} ...", applicationDate, productId, brandId);
        return Optional.ofNullable(
                this.priceRepository.findApplicablePrice(applicationDate, productId, brandId))
            .orElse(List.of()); 
    }

}
