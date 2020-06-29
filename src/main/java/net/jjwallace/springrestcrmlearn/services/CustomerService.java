package net.jjwallace.springrestcrmlearn.services;

import java.util.List;

import net.jjwallace.springrestcrmlearn.entity.Customer;

public interface CustomerService {

	public List<Customer> getCustomers();

	public void deleteCustomer(Customer customer);
	
	public void updateCustomer(Customer customer);

	public void addCustomer(Customer theCustomer);

	public Customer getCustomer(int customerId);

	public List<Customer> getCustomers(String firstName, String lastName);
	
	public List<Customer> getCustomers(String firstName);

	public int getMaxCustomerId();

}
