package net.jjwallace.springrestcrmlearn.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.jjwallace.springrestcrmlearn.doa.CustomerDOA;
import net.jjwallace.springrestcrmlearn.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerDOA customerDOA;

	@Override
	@Transactional
	public List<Customer> getCustomers() {
		return customerDOA.getCustomers();
	}

	@Override
	@Transactional
	public void deleteCustomer(Customer customer) {
		customerDOA.deleteCustomer(customer);
		
	}

	@Override
	@Transactional
	public void updateCustomer(Customer customer) {
		customerDOA.updateCustomer(customer);
		
	}

	@Override
	@Transactional
	public void addCustomer(Customer theCustomer) {
		customerDOA.addCustomer(theCustomer);
		
	}

	@Override
	@Transactional
	public Customer getCustomer(int customerId) {
		return customerDOA.getCustomer(customerId);
	}

	@Override
	@Transactional
	public List<Customer> getCustomers(String firstName, String lastName) {
		return customerDOA.getCustomers(firstName, lastName);
	}

	@Override
	@Transactional
	public List<Customer> getCustomers(String firstName) {
		return customerDOA.getCustomers(firstName);
	}

	@Override
	@Transactional
	public int getMaxCustomerId() {
		return customerDOA.getMaxCustomerId();
	}

}
