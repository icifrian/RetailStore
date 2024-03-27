package es.retail.store.infrastructure.api.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import es.retail.store.infrastructure.config.spring.RetailStoreApplication;
import es.retail.store.utils.TestUtils;

@SpringBootTest(classes = RetailStoreApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("local")
@Sql({"/data.sql" })
class PriceControllerIntegrationTest {
	
	private static final String JSON_PATH = "src/test/resources/";


	@Autowired
	private MockMvc mockMvc;
	 
	@Autowired
	private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void  getPriceForProduct35455At10OnJune14_Test() throws Exception {
    	MvcResult response =  mockMvc.perform(get("/api/prices")
                .param("applicationDate", "2020-06-14T10:00:00")
                .param("productId", "35455")
                .param("brandId", "1")).andReturn();
        
        String priceJsonFile = JSON_PATH + "Product35455At10OnJune14.json";
        String expectedEntity = TestUtils.toJson(priceJsonFile);
        String actualEntity = response.getResponse().getContentAsString();
        
        Assertions.assertEquals(HttpStatus.OK.value(), response.getResponse().getStatus());
        JSONAssert.assertEquals(expectedEntity, actualEntity, JSONCompareMode.LENIENT);
    }

    @Test
    public void getPriceForProduct35455At16On14th_Test() throws Exception {
        mockMvc.perform(get("/api/prices")
                .param("applicationDate", "2020-06-14T16:00:00")
                .param("productId", "35455")
                .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].price").value(25.45));
    }

    @Test
    public void getPriceForProduct35455At21On14th_Test() throws Exception {
        mockMvc.perform(get("/api/prices")
                .param("applicationDate", "2020-06-14T21:00:00")
                .param("productId", "35455")
                .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].price").value(35.50));
    }

    @Test
    public void getPriceForProduct35455At10On15th_Test() throws Exception {
        mockMvc.perform(get("/api/prices")
                .param("applicationDate", "2020-06-15T10:00:00")
                .param("productId", "35455")
                .param("brandId", "1"))
                .andExpect(status().isOk())
       			.andExpect(jsonPath("$[0].price").value(30.50));
    }

    @Test
    public void getPriceForProduct35455At21On16th_Test() throws Exception {
        mockMvc.perform(get("/api/prices")
                .param("applicationDate", "2020-06-16T21:00:00")
                .param("productId", "35455")
                .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].price").value(38.95));
    }
}
