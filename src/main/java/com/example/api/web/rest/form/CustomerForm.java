package com.example.api.web.rest.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.api.domain.Customer;

public class CustomerForm {

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
	public Customer convert() {
		return new Customer(name, email);
	}
	
}
