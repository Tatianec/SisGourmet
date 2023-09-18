package com.prss6.sisgourmet.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.prss6.sisgourmet.model.Employee;
import com.prss6.sisgourmet.repository.EmployeeRepository;
import com.prss6.sisgourmet.service.EmployeeService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductResource {
	
	@Autowired
	private EmployeeRepository productRepository;
	
	@Autowired
	private EmployeeService productService;
	
	@GetMapping
	public List<Employee> list(){
		return productRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Employee create(@Valid @RequestBody Employee product, 
			HttpServletResponse response) {
		return productRepository.save(product);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> findById(@PathVariable Long id){
		Optional<Employee> product = productRepository.findById(id);
		if(product.isPresent()) {
			return ResponseEntity.ok(product.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long id) {
		productRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Employee> update(@PathVariable Long id,
			@Valid @RequestBody Employee product){
		Employee productSaved = productService.update(id, product);
		return ResponseEntity.ok(productSaved);
	}

	
	
}
