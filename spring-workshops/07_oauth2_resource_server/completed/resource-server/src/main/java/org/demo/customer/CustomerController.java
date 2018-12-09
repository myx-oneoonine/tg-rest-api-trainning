package org.demo.customer;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.demo.customer.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@GetMapping("/{id}")
	public ResponseEntity<?> getCustomer(@PathVariable Long id) {
		Optional<Customer> customer = customerService.retrieveCustomer(id);

		if (!customer.isPresent()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(customer);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> putCustomer(@PathVariable Long id, @Valid @RequestBody Customer body) {
		if (!customerService.updateCustomer(id, body)) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
		if (!customerService.deleteCustomer(id)) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@GetMapping(params = "name")
	public ResponseEntity<?> getCustomer(@RequestParam(value = "name") String name) {
		List<Customer> customerList = customerService.retrieveCustomer(name);

		if (customerList.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(customerList);
		}
	}

}