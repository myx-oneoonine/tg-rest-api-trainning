package org.demo.customer;

import java.util.List;

import org.demo.customer.entities.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	List<Customer> findByFirstName(String firstName);
}
