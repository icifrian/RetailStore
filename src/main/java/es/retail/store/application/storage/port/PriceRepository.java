package es.retail.store.application.storage.port;

import java.time.LocalDateTime;
import java.util.List;

import es.retail.store.domain.model.Prices;

public interface PriceRepository {

	 List<Prices> findApplicablePrice(LocalDateTime applicationDate, Long productId, Long brandId);
}
