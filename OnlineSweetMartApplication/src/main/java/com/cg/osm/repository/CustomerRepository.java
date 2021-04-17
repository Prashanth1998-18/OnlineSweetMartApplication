package com.cg.osm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.osm.entity.Customer;
import com.cg.osm.error.CustomerNotFoundException;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
//	public Customer addCustomer(Customer Customer);
//	public Customer updateCustomer(Customer Customer) throws CustomerNotFoundException;
//	public Customer cancelCustomer(int CustomerId) throws CustomerNotFoundException;
//	public List<Customer> showAllCustomers();
//	public Optional<Customer> findCustomerById(int CustomerId);

	@Query(value = "from Customer where :CustomerName = name")
	public Optional<Customer> findCustomerByName(@Param("CustomerName")String CustomerName);
}
