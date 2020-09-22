package com.example.api.web.rest;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.api.domain.Customer;
import com.example.api.service.CustomerService;
import com.example.api.web.rest.dto.CustomerDto;
import com.example.api.web.rest.form.CustomerForm;
import com.example.api.web.rest.form.UpdateCustomerForm;;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private CustomerService service;
	@Autowired
	public CustomerController(CustomerService service) {
		this.service = service;
	}

	@GetMapping
	public Page<CustomerDto> findAll(@RequestParam int pag, @RequestParam int qtd) {
		
		Pageable pagination = PageRequest.of(pag, qtd);
		Page<Customer> customers = service.findAll(pagination);
		return CustomerDto.convert(customers);
	}

	@GetMapping("/{id}")
	public CustomerDto getById(@PathVariable Long id) {
		Customer customer = service.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
		return new CustomerDto(customer);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<CustomerDto> create(@RequestBody @Valid CustomerForm form, UriComponentsBuilder uriBuilder) {
		Customer customer = form.convert();
		service.save(customer);
		
		URI uri = uriBuilder.path("/customers/{id}").buildAndExpand(customer.getId()).toUri();
		return ResponseEntity.created(uri).body(new CustomerDto(customer));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<CustomerDto> update(@PathVariable Long id, @RequestBody @Valid UpdateCustomerForm form){
		Customer customer = form.update(id, service);
		return ResponseEntity.ok(new CustomerDto(customer));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CustomerDto> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.ok().build();
	}

}
