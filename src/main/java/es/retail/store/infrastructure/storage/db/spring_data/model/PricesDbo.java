package es.retail.store.infrastructure.storage.db.spring_data.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "prices")
public class PricesDbo {

	@Id
	@Column(name = "id") 
    private Long id;
	
	@Column(name = "BRAND_ID") 
    private int brandId;
	
	@Column(name = "START_DATE") 
    private LocalDateTime startDate;
	
	@Column(name = "END_DATE") 
    private LocalDateTime endDate;
	
	@Column(name = "PRICE_LIST") 
    private int priceList;
	
	@Column(name = "PRODUCT_ID") 
    private Long productId;
	
	@Column(name = "PRIORITY") 
    private int priority;
	
	@Column(name = "PRICE") 
    private BigDecimal price;
	
	@Column(name = "CURR") 
    private String curr;
    
}
