package es.retail.store.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public record  Prices (	
	int brandId,
	Long productId,
	int priceList,
	LocalDateTime startDate,
	LocalDateTime endDate,
	BigDecimal price)
{
}