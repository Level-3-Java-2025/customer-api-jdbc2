package net.groundgurus.customer_api_jdbc2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.groundgurus.customer_api_jdbc2.model.Customer;
import net.groundgurus.customer_api_jdbc2.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {
	private MockMvc mockMvc;
	private ObjectMapper objectMapper;

	@Mock
	private CustomerService customerService;

	@InjectMocks
	private CustomerController customerController;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
		objectMapper = new ObjectMapper();
	}

	@Test
	public void testGetAllCustomers() throws Exception {
		when(customerService.getAllCustomers()).thenReturn(
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

		mockMvc.perform(get("/api/customers"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].id").value(1))
				.andExpect(jsonPath("$[0].firstName").value("John"))
				.andExpect(jsonPath("$[0].lastName").value("Doe"));

		verify(customerService).getAllCustomers();
	}
}