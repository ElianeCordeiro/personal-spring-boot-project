package com.mypersonal.project.springboot.service;

import java.util.List;

import com.mypersonal.project.springboot.dto.request.CustomerDtoRequest;
import com.mypersonal.project.springboot.dto.response.CustomerDtoResponse;

public interface CustomerService {
	
	CustomerDtoResponse createCustomer(CustomerDtoRequest customer);
	
	CustomerDtoResponse getCustomerbyId(Integer id);
	
	List<CustomerDtoResponse> getAllCustomers();
	
	CustomerDtoResponse updateCustomer(CustomerDtoRequest customerDtoRequest, Integer id);
	
	void deleteCustomerById(Integer id);
}
