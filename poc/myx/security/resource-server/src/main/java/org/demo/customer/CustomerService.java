package org.demo.customer;

import java.util.List;
import java.util.Optional;

import org.demo.customer.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	public List<Customer> retrieveCustomer() {
		return (List<Customer>) customerRepository.findAll();
	}

	public Optional<Customer> retrieveCustomer(Long id) {
		return customerRepository.findById(id);
	}

	public List<Customer> retrieveCustomer(String name) {
		return customerRepository.findByFirstNameContainingOrderByFirstNameAsc(name);
	}

	public Customer createCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	public boolean updateCustomer(Long id, Customer customer) {
		Optional<Customer> customerOptional = customerRepository.findById(id);

		if (!customerOptional.isPresent()) {
			return false;
		} else {
			customer.setId(id);
			Optional.of(customerRepository.save(customer));

			return true;
		}
	}

	public boolean deleteCustomer(Long id) {
		try {
			customerRepository.deleteById(id);
			return true;
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
	}
}
