package com.example.api.web.rest.dto;


import org.springframework.data.domain.Page;

import com.example.api.domain.Customer;

public class CustomerDto {

	private Long id;
	private String name;
	private String email;
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public CustomerDto(Customer customer) {
		this.id = customer.getId();
		this.name = customer.getName();
		this.email = customer.getEmail();
	}

	public static Page<CustomerDto> convert(Page<Customer> customers) {
		return customers.map(CustomerDto::new);
	}

}
