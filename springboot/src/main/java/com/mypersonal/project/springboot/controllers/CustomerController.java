package com.mypersonal.project.springboot.controllers;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mypersonal.project.springboot.dto.request.CustomerDtoRequest;
import com.mypersonal.project.springboot.dto.response.CustomerDtoResponse;
import com.mypersonal.project.springboot.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	private CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@PostMapping
	public ResponseEntity<CustomerDtoResponse> createCustomer(@Valid @RequestBody CustomerDtoRequest customerDtoRequest){
		return new ResponseEntity<>(customerService.createCustomer(customerDtoRequest), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomerDtoResponse> getCustomerById(@PathVariable(name = "id") Integer id){
		return ResponseEntity.ok(customerService.getCustomerbyId(id));
	}
	
	@GetMapping
	public ResponseEntity<List<CustomerDtoResponse>> findAllCustomers(){
		//var is a generic type, that takes the implicit type beside the context
		//it's a good practice?
		var findAll = customerService.getAllCustomers();
		return ResponseEntity.status(HttpStatus.OK).body(findAll);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CustomerDtoResponse> updateCustomer(@Valid @RequestBody CustomerDtoRequest customerDtoRequest, @PathVariable(name="id") Integer id){
		
		CustomerDtoResponse customerDtoResponse = customerService.updateCustomer(customerDtoRequest, id);
		
		return new ResponseEntity<>(customerDtoResponse, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable(name="id") Integer id){
		
		customerService.deleteCustomerById(id);
		return new ResponseEntity<>("Customer entity deleted successfully", HttpStatus.OK);
	}
}
