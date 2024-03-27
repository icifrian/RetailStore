package es.retail.store.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PriceResponseDto  (
		Long productId,
	    int brandId,
	    int priceList,
	    LocalDateTime startDate,
	    LocalDateTime endDate,
	    BigDecimal price,
	    String currency
		) {

}
