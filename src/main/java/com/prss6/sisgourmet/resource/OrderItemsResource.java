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

import com.prss6.sisgourmet.model.Pedido;
import com.prss6.sisgourmet.repository.OrderRepository;
import com.prss6.sisgourmet.service.OrderService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/order-items")
public class OrderItemsResource {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping
	public List<Pedido> list(){
		return orderRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pedido create(@Valid @RequestBody Pedido order, 
			HttpServletResponse response) {
		return orderRepository.save(order);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> findById(@PathVariable Long id){
		Optional<Pedido> order = orderRepository.findById(id);
		if(order.isPresent()) {
			return ResponseEntity.ok(order.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long id) {
		orderRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pedido> update(@PathVariable Long id,
			@Valid @RequestBody Pedido order){
		Pedido orderSaved = orderService.update(id, order);
		return ResponseEntity.ok(orderSaved);
	}

	
	
}
