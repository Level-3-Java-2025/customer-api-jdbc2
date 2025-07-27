package net.groundgurus.customer_api_jdbc2.service;

import net.groundgurus.customer_api_jdbc2.model.Customer;
import net.groundgurus.customer_api_jdbc2.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
	@Mock
	private CustomerRepository customerRepository;

	@InjectMocks
	private CustomerService customerService;

	@Test
	public void testGetAllCustomers() {
		when(customerRepository.findAll()).thenReturn(
				List.of(
						Customer.builder()
								.id(1)
								.firstName("John")
								.lastName("Doe")
								.build(),
						Customer.builder()
								.id(2)
								.firstName("Jane")
								.lastName("Doe")
								.build()
				)
		);
		var allCustomers = customerService.getAllCustomers();

		assertNotNull(allCustomers);
		assertFalse(allCustomers.isEmpty());
		assertEquals(2, allCustomers.size());
		assertEquals("John", allCustomers.getFirst().getFirstName());
		assertEquals("Doe", allCustomers.getFirst().getLastName());
	}

	@Test
	public void testGetCustomerById() {
		when(customerRepository.findById(1)).thenReturn(
				Customer.builder()
						.id(1)
						.firstName("John")
						.lastName("Doe")
						.build()
		);

		var customer = customerService.getCustomerById(1);
		assertNotNull(customer);
		assertEquals("John", customer.getFirstName());
		assertEquals("Doe", customer.getLastName());
	}

	@Test
	public void testGetCustomerById_NotFound() {
		when(customerRepository.findById(1)).thenReturn(null);

		var customer = customerService.getCustomerById(1);
		assertNull(customer);
	}

	@Test
	public void testSaveCustomer() {
		customerService.saveCustomer(Customer.builder()
				.id(1)
				.firstName("John")
				.lastName("Doe")
				.build());
		verify(customerRepository).save(any(Customer.class));
	}

	@Test
	public void testDeleteCustomerById() {
		customerService.deleteCustomerById(1);
		verify(customerRepository).deleteById(any(Long.class));
	}

	@Test
	public void testUpdateCustomer() {
		var customer = Customer.builder()
				.id(1)
				.firstName("John")
				.lastName("Doe")
				.build();
		customerService.updateCustomer(customer);
		verify(customerRepository).save(any(Customer.class));
	}
}
