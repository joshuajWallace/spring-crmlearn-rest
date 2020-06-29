package net.jjwallace.springrestcrmlearn.doa;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.jjwallace.springrestcrmlearn.entity.Customer;

@Repository
public class CustomerDOAImpl implements CustomerDOA {
	
	@Autowired
	SessionFactory sessionFactory;
	
	
	
	@Override
	public List<Customer> getCustomers() {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Customer> query = currentSession.createQuery("from Customer order by lastName", Customer.class);
				
		return query.getResultList();
	}
	
	@Override
	public void addCustomer(Customer customer) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.save(customer);	
		
	}
	
	@Override
	public void deleteCustomer(Customer customer) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		
		currentSession.delete(customer);	
		
	}
	@Override
	public void updateCustomer(Customer updatedCustomer) {
		
	
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.update(updatedCustomer);
		
			
	}

	@Override
	public Customer getCustomer(int customerId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
				
		return currentSession.get(Customer.class, customerId);
	}

	@Override
	public List<Customer> getCustomers(String firstName, String lastName) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Customer> query = currentSession.createQuery("from Customer where firstName= :fname and lastName= :lname order by lastName", Customer.class);
		query.setParameter("fname", firstName);
		query.setParameter("lname", lastName);
		
		return query.getResultList();
	}

	@Override
	public List<Customer> getCustomers(String firstName) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Customer> query = currentSession.createQuery("from Customer  where firstName = :fname order by lastName", Customer.class);
		query.setParameter("fname", firstName);
		
		return query.getResultList();
	}

	@Override
	public int getMaxCustomerId() {
		Session currentSession = sessionFactory.getCurrentSession();
		
		int	query = (int)currentSession.createQuery("select max(id) from Customer customer").getSingleResult();

		return query;
	}

}
