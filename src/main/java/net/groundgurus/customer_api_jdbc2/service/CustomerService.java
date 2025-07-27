package net.groundgurus.customer_api_jdbc2.service;

import lombok.RequiredArgsConstructor;
import net.groundgurus.customer_api_jdbc2.model.Customer;
import net.groundgurus.customer_api_jdbc2.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
	private final CustomerRepository customerRepository;

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	public Customer getCustomerById(long id) {
		return customerRepository.findById(id);
	}

	public void saveCustomer(Customer customer) {
		customerRepository.save(customer);
	}

	public void deleteCustomerById(long id) {
		customerRepository.deleteById(id);
	}

	public void updateCustomer(Customer customer) {
		customerRepository.save(customer);
	}
}
