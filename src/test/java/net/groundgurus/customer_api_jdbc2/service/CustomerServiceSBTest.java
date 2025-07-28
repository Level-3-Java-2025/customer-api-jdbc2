package net.groundgurus.customer_api_jdbc2.service;

import net.groundgurus.customer_api_jdbc2.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CustomerServiceSBTest {
	@Autowired
	private CustomerService customerService;

	@Test
	public void getAllCustomers() {
		createCustomer("John", "Doe");
		createCustomer("Jane", "Smith");

		var customers = customerService.getAllCustomers();

		assertNotNull(customers);
		assertFalse(customers.isEmpty());
		assertEquals(2, customers.size());
		assertEquals("John", customerService.getAllCustomers().getFirst().getFirstName());
		assertEquals("Doe", customerService.getAllCustomers().getFirst().getLastName());
	}

	private void createCustomer(String firstName, String lastName) {
		customerService.saveCustomer(
				Customer.builder()
						.firstName(firstName)
						.lastName(lastName)
						.build()
		);
	}
}
