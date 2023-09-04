package com.mypersonal.project.springboot.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.mypersonal.project.springboot.dto.request.CustomerDtoRequest;
import com.mypersonal.project.springboot.dto.response.CustomerDtoResponse;
import com.mypersonal.project.springboot.entities.CustomerEntity;
import com.mypersonal.project.springboot.exceptions.ResourceNotFoundException;
import com.mypersonal.project.springboot.repositories.CostumerRepository;
import com.mypersonal.project.springboot.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	private CostumerRepository  customerRepository;
	
	private ModelMapper mapper;
	
	public CustomerServiceImpl(CostumerRepository customerRepository, ModelMapper mapper) {
		this.customerRepository = customerRepository;
		this.mapper = mapper;
	}

	@Override
	public CustomerDtoResponse createCustomer(CustomerDtoRequest customerDtoRequest) {

		CustomerEntity customerEntity = mapToEntity(customerDtoRequest);
		customerRepository.save(customerEntity);
		
		CustomerDtoResponse savedCustomerResponse = mapToDto(customerEntity);
		
		return savedCustomerResponse;
	}

	@Override
	public CustomerDtoResponse getCustomerbyId(Integer id) {
		
		CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
		
		return mapToDto(customerEntity);
	}
	
	@Override
	public CustomerDtoResponse updateCustomer(CustomerDtoRequest customerDtoRequest, Integer id) {
		
		CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
		
		customerEntity.setName(customerDtoRequest.getName());
		customerEntity.setEmail(customerDtoRequest.getEmail());
		customerEntity.setAge(customerDtoRequest.getAge());
		
		CustomerEntity updatedCustomer = customerRepository.save(customerEntity);
		
		return mapToDto(updatedCustomer);
		
	}
	
	@Override
	public List<CustomerDtoResponse> getAllCustomers() {
	
		List<CustomerEntity> customerEntities = customerRepository.findAll();
		
		List<CustomerDtoResponse> customerDtoResponses = new ArrayList<>();
		
		
		//colocar em uma lambda expression
		for (CustomerEntity customerEntity : customerEntities) {
			customerDtoResponses.add(mapToDto(customerEntity));
		}
		
		return customerDtoResponses;
	}

	private CustomerDtoResponse mapToDto(CustomerEntity customer) {
		CustomerDtoResponse customerDtoResponse = mapper.map(customer, CustomerDtoResponse.class);
		
		return customerDtoResponse;
	}
	
	private CustomerEntity mapToEntity(CustomerDtoRequest customerDtoRequest) {
		CustomerEntity customerEntity = mapper.map(customerDtoRequest, CustomerEntity.class);
		
		return customerEntity;
	}

	@Override
	public void deleteCustomerById(Integer id) {
		
		CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
		
		customerRepository.delete(customerEntity);
	}

	
}
