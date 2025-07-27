package net.groundgurus.customer_api_jdbc2.controller;

import lombok.RequiredArgsConstructor;
import net.groundgurus.customer_api_jdbc2.model.Customer;
import net.groundgurus.customer_api_jdbc2.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
	private final CustomerService customerService;

	@GetMapping
	public ResponseEntity<List<Customer>> getAllCustomers() {
		var customers = customerService.getAllCustomers();
//		return new ResponseEntity<>(customers, HttpStatus.OK);
		return ResponseEntity.ok().body(customers);
	}

	@GetMapping("{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable int id) {
		var customer = customerService.getCustomerById(id);
		return ResponseEntity.ok().body(customer);
	}

	@PostMapping
	public ResponseEntity<Void> saveCustomer(@RequestBody Customer customer) {
		customerService.saveCustomer(customer);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable int id) {
		customerService.deleteCustomerById(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping("{id}")
	public ResponseEntity<Void> updateCustomer(@PathVariable int id, @RequestBody Customer customer) {
		customer.setId(id);
		customerService.updateCustomer(customer);
		return ResponseEntity.ok().build();
	}
}
