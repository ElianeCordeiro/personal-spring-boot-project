package com.mypersonal.project.springboot.dto.response;

import lombok.Data;

@Data
public class CustomerDtoResponse {

	private Integer id;
	
	private String name;
	
	private String email;
	
	private Integer age;
}
