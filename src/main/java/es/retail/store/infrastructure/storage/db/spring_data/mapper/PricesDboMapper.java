package es.retail.store.infrastructure.storage.db.spring_data.mapper;

import org.mapstruct.Mapper;

import es.retail.store.domain.model.Prices;
import es.retail.store.infrastructure.storage.db.spring_data.model.PricesDbo;

@Mapper(componentModel = "spring")
public interface PricesDboMapper {

	Prices toDomain(PricesDbo priceDbo);
}
