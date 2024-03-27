package es.retail.store.infrastructure.config.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.retail.store.application.service.impl.PriceServiceImpl;
import es.retail.store.application.service.port.PriceService;
import es.retail.store.application.storage.port.PriceRepository;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class RetailStoreApplicationConfig {

	@Bean
	  public PriceService priceService(PriceRepository priceRepository) {
		return new PriceServiceImpl(priceRepository);
	}
}
