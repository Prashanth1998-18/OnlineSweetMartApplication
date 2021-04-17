package com.cg.osm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.osm.entity.SweetOrder;
@Repository
public interface SweetOrderJpaRepository extends JpaRepository<SweetOrder,Integer> {
	  
//	@Query( "select sum(s.get().getProdList().getPrice()) from SweetOrder s where sweetOrderId=:sweetOrderId")
//	public double calculateTotalCost(@Param("sweetOrderId")int sweetOrderId);
	
//	@Query(value = " from SweetOrder WHERE customer_userId IN :customerId")
	@Query("SELECT s FROM SweetOrder s WHERE s.customer.userId = ?1")
	public List<SweetOrder> findOrdersByCustomerId(@Param("customerId")int customerId);
	
	
}