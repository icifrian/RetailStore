package es.retail.store.application.service.port;

import java.time.LocalDateTime;
import java.util.List;

import es.retail.store.domain.model.Prices;

public interface PriceService {

	List<Prices>  findApplicablePrice(LocalDateTime applicationDate, Long productId, Long brandId);
}
