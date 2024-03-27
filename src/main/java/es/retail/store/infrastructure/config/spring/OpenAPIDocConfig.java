package es.retail.store.infrastructure.config.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIDocConfig {

	@Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                		.title("Retail Store e-commerce API")
                		.version("1.0")
                		.description("Retail Store e-commerce API v1.0"));
    }
}
