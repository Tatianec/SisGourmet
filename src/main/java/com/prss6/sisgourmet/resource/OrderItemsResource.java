package com.prss6.sisgourmet.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.prss6.sisgourmet.model.OrderItems;
import com.prss6.sisgourmet.repository.OrderItemsRepository;
import com.prss6.sisgourmet.service.OrderItemsService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/orderItems")
public class OrderItemsResource {
	
	@Autowired
	private OrderItemsRepository orderItemsRepository;
	
	@Autowired
	private OrderItemsService orderItemsService;
	
	@GetMapping
	public List<OrderItems> list(){
		return orderItemsRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrderItems create(@Valid @RequestBody OrderItems orderItems, 
			HttpServletResponse response) {
		return orderItemsRepository.save(orderItems);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderItems> findById(@PathVariable Long id){
		Optional<OrderItems> orderItems = orderItemsRepository.findById(id);
		if(orderItems.isPresent()) {
			return ResponseEntity.ok(orderItems.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long id) {
		orderItemsRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<OrderItems> update(@PathVariable Long id,
			@Valid @RequestBody OrderItems orderItems){
		OrderItems orderItemsSaved = orderItemsService.update(id, orderItems);
		return ResponseEntity.ok(orderItemsSaved);
	}
	
}
