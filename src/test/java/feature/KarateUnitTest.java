package feature;

import org.springframework.boot.test.context.SpringBootTest;

import com.intuit.karate.junit5.Karate;

import es.retail.store.infrastructure.config.spring.RetailStoreApplication;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = {RetailStoreApplication.class})
class KarateUnitTest {
	
    @Karate.Test
    Karate testAll() {
//        return Karate.run().relativeTo(getClass());
        return Karate.run("classpath:feature/getPrices.feature");
    }
}
