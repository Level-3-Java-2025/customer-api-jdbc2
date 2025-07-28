package net.groundgurus.customer_api_jdbc2.controller;

import net.groundgurus.customer_api_jdbc2.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CustomerControllerSBTest {
	@Autowired
	private TestRestTemplate restTemplate;
	private static final String CUSTOMER_API = "/api/customers";

	@Test
	public void getAllCustomers() {
		createCustomer("John", "Doe");
		createCustomer("Jane", "Smith");

		var response = restTemplate.exchange(CUSTOMER_API, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Customer>>() {});

		var customers = response.getBody();
		assertNotNull(customers);
		assertFalse(customers.isEmpty());
		assertEquals("John", customers.getFirst().getFirstName());
		assertEquals("Doe", customers.getFirst().getLastName());
	}

	private void createCustomer(String firstName, String lastName) {
		var customer = Customer.builder().firstName(firstName).lastName(lastName).build();
		restTemplate.postForEntity(CUSTOMER_API, customer, customer.getClass());
	}
}
