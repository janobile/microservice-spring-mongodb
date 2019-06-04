package com.example.demo.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.*;
import com.example.demo.repository.CustomerRepository;

import java.util.List;

import javax.validation.Valid;


@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;
	
	/*
	 * private final CustomerRepository customerRepository;
	 * 
	 * public CustomerController(CustomerRepository customerRepository) { super();
	 * this.customerRepository = customerRepository; }
	 */
	 
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Customer> getAllCustomer(){
		return customerRepository.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Customer getCustomerById(@PathVariable("id") ObjectId id) {
		return customerRepository.findBy_id(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void modifyCustomerById(@PathVariable("id") ObjectId id,@Valid @RequestBody Customer customer) {
		customer.set_id(id);
		customerRepository.save(customer);
	}
	
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Customer createCustomer(@Valid @RequestBody Customer customer) {
		customer.set_id(ObjectId.get());
		customerRepository.save(customer);
		return customer;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteCustomer(@PathVariable ObjectId id) {
		customerRepository.delete(customerRepository.findBy_id(id));
	}
}
