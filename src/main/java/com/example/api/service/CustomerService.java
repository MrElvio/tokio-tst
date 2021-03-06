package com.example.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.api.domain.Customer;
import com.example.api.repository.CustomerRepository;

@Service
public class CustomerService {

	private CustomerRepository repository;

	@Autowired
	public CustomerService(CustomerRepository repository) {
		this.repository = repository;
	}

	public Page<Customer> findAll(Pageable pagination) {
		return (Page<Customer>) repository.findAllByOrderByNameAsc(pagination);
	}

	public Optional<Customer> findById(Long id) {
		return repository.findById(id);
	}
	
	public void save(Customer customer) {
		repository.save(customer);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

}
