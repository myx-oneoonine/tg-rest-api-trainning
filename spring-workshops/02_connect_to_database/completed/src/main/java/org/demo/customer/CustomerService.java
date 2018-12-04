package org.demo.customer;

import java.util.List;

import org.demo.customer.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

	private CustomerRepository repository;

	@Autowired
	public CustomerService(CustomerRepository repository) {
		this.repository = repository;
	}

	public List<Customer> retrieveCustomer() {
		return (List<Customer>) repository.findAll();
	}

}
