package com.mypersonal.project.springboot.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDtoRequest {

	@NotEmpty
	@Size(min=2, message = "Customer name should have at least 2 characters ")
	private String name;
	
	@NotEmpty
	@Email
	private String email;
	
	@NotNull
	private Integer age;
}
