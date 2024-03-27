package es.retail.store.infrastructure.storage.db.spring_data.jpa;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.retail.store.infrastructure.storage.db.spring_data.model.PricesDbo;

@Repository
public interface JpaPriceRepository extends JpaRepository<PricesDbo, Long> {

	@Query("SELECT p FROM PricesDbo p WHERE p.productId = :productId AND p.brandId = :brandId AND :applicationDate BETWEEN p.startDate AND p.endDate ORDER BY p.priority DESC, p.startDate DESC")
	List<PricesDbo> findApplicablePrices(@Param("applicationDate") LocalDateTime applicationDate, @Param("productId") Long productId, @Param("brandId") int brandId);
}