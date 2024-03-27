package es.retail.store.infrastructure.storage.db.spring_data.adapter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import es.retail.store.application.storage.port.PriceRepository;
import es.retail.store.domain.model.Prices;
import es.retail.store.infrastructure.storage.db.spring_data.jpa.JpaPriceRepository;
import es.retail.store.infrastructure.storage.db.spring_data.mapper.PricesDboMapper;
import es.retail.store.infrastructure.storage.db.spring_data.model.PricesDbo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Repository
public class PriceRepositoryAdapter implements PriceRepository {

	private final JpaPriceRepository jpaPriceRepository;
	private final PricesDboMapper priceDboMapper;

	
	@Override
	public List<Prices> findApplicablePrice(LocalDateTime applicationDate, Long productId, Long brandId) {
		log.info("Looking for a Price...");

		List<PricesDbo> listPrices = jpaPriceRepository.findApplicablePrices(applicationDate, productId, brandId.intValue());

		if (CollectionUtils.isEmpty(listPrices)) {
		      log.info("Prices not found with applicationDate={}, productId={} and brandId={}", applicationDate, productId, brandId);
		      throw new NoSuchElementException("Price not found");
	    }
	    log.info("{} Prices found with applicationDate={}, productId={} and brandId={}", listPrices.size(), applicationDate, productId, brandId);

	    return listPrices.stream().map(priceDboMapper::toDomain).collect(Collectors.toList());
	}

}
