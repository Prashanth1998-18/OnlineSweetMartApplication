package com.cg.osm.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.cg.osm.entity.SweetOrder;
import com.cg.osm.error.CustomerNotFoundException;
import com.cg.osm.error.ProductNotFoundException;
import com.cg.osm.error.SweetOrderNotFoundException;
import com.cg.osm.service.SweetOrderServiceImpl;

@RestController
public class SweetController {
    @Autowired
	private SweetOrderServiceImpl sweetService;
   
    @PostMapping(path="/sweetorder")
	public SweetOrder addSweetOrder(@RequestBody @Valid SweetOrder sweetorder) throws CustomerNotFoundException, ProductNotFoundException{/*@PathVariable("customerId") int customerId)*/ 
		return sweetService.addSweetOrder(sweetorder);
	}
    
    @DeleteMapping(path="/sweetorder/{sweetOrderId}")
	public String cancelSweetOrder(@PathVariable("sweetOrderId") int sweetOrderId) throws SweetOrderNotFoundException{
    	return sweetService.cancelSweetOrder(sweetOrderId);
	}
    
    @GetMapping(path="/sweetorder")
    public List<SweetOrder> retreiveAllSweet() throws SweetOrderNotFoundException
    {
     return sweetService.ShowAllSweetOrder();
    }
 
    
    @GetMapping(path="/sweetorder/cust/{customerId}")
	public List<SweetOrder> findOrdersByCustomerId(@PathVariable("customerId") int customerId) throws CustomerNotFoundException{
		
    	return sweetService.findOrdersByCustomerId(customerId);
	}
    
    @GetMapping(path="/sweetorder/{orderId}")
    public SweetOrder findOrderBySweetOrderId(@PathVariable("orderId") int orderId) throws SweetOrderNotFoundException{
    	Optional<SweetOrder> sweetorder=sweetService.findOrderById(orderId);
    	return sweetorder.get();
    }
}