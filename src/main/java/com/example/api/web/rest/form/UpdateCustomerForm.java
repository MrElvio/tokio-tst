package com.example.api.web.rest.form;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.example.api.domain.Customer;
import com.example.api.service.CustomerService;

public class UpdateCustomerForm {

	@NotNull @NotEmpty
	private String name;
	@NotNull @NotEmpty
	private String email;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Customer update(Long id, CustomerService service) {
		Customer customer = service.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));;
		customer.setName(this.getName());
		customer.setEmail(this.getEmail());
		return customer;
	}
	
}
