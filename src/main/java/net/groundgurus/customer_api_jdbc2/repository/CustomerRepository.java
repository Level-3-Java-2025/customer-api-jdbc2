package net.groundgurus.customer_api_jdbc2.repository;

import net.groundgurus.customer_api_jdbc2.model.Customer;
import org.springframework.data.repository.ListCrudRepository;

public interface CustomerRepository extends ListCrudRepository<Customer, Long> {
	Customer findById(long id);
}
