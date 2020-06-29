package net.jjwallace.springrestcrmlearn.doa;

import java.util.List;

import net.jjwallace.springrestcrmlearn.entity.Customer;

public interface CustomerDOA {
	
	public List<Customer> getCustomers();

	public void addCustomer(Customer customer);

	public void deleteCustomer(Customer customer);

	public void updateCustomer(Customer updatedCustomer);
	
	public Customer getCustomer(int customerId);

	public List<Customer> getCustomers(String firstName, String lastName);

	public List<Customer> getCustomers(String firstName);

	public int getMaxCustomerId();

	

}
