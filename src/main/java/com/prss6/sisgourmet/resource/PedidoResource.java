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
import com.prss6.sisgourmet.repository.PedidoRepository;
import com.prss6.sisgourmet.service.PedidoService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedido")
public class PedidoResource {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping
	public List<Pedido> list(){
		return pedidoRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pedido create(@Valid @RequestBody Pedido pedido, 
			HttpServletResponse response) {
		return pedidoRepository.save(pedido);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> findById(@PathVariable Long id){
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		if(pedido.isPresent()) {
			return ResponseEntity.ok(pedido.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long id) {
		pedidoRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pedido> update(@PathVariable Long id,
			@Valid @RequestBody Pedido pedido){
		Pedido pedidoSaved = pedidoService.update(id, pedido);
		return ResponseEntity.ok(pedidoSaved);
	}
	
}
