package org.demo.customer;

import java.util.List;

import org.demo.customer.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@GetMapping
	public List<Customer> getCustomers() {
		return customerService.retrieveCustomer();
	}
}