package es.retail.store.infrastructure.storage.db.spring_data.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
		basePackages  = "es.retail.store.infrastructure.storage.db.spring_data.jpa")
@EntityScan(basePackages = "es.retail.store.infrastructure.storage.db.spring_data.model")
public class SpringDataConfig {

}
