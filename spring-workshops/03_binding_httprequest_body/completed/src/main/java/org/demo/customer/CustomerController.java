package org.demo.customer;

import java.util.List;

import javax.validation.Valid;

import org.demo.customer.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@PostMapping()
	public ResponseEntity<?> postCustomer(@Valid @RequestBody Customer body) {
		Customer customer = customerService.createCustomer(body);

		return ResponseEntity.status(HttpStatus.CREATED).body(customer);
	}
}