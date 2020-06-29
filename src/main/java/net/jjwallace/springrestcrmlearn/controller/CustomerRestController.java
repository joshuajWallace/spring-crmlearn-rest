package net.jjwallace.springrestcrmlearn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.jjwallace.springrestcrmlearn.entity.Customer;
import net.jjwallace.springrestcrmlearn.exceptions.CustomerNotFoundException;
import net.jjwallace.springrestcrmlearn.services.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customers")
	public List<Customer> getCustomers(){
			return customerService.getCustomers();
		}
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId){
		if(customerId >= customerService.getMaxCustomerId() || customerId < 0)
			throw new CustomerNotFoundException("No Customer with the ID : " + customerId);
		return customerService.getCustomer(customerId);
	}
	
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer newCustomer) {
		newCustomer.setId(0);
		customerService.addCustomer(newCustomer);
		return newCustomer;
	}
	
	@PutMapping("/customers")
	public Customer patchCustomer(@RequestBody Customer updateCustomer) {
		customerService.updateCustomer(updateCustomer);
		return updateCustomer;
	}
	
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {
		Customer deletedCustomer = customerService.getCustomer(customerId);
		if(deletedCustomer == null)
			throw new CustomerNotFoundException("No Customer with the ID : " + customerId);
		customerService.deleteCustomer(deletedCustomer);
		return "Deleted Customer with id " + customerId;
	}
}

