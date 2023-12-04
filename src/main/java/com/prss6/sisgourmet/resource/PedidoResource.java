package com.prss6.sisgourmet.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
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

import com.prss6.sisgourmet.model.Pedido;
import com.prss6.sisgourmet.model.PedidoProduct;
import com.prss6.sisgourmet.repository.PedidoRepository;
import com.prss6.sisgourmet.service.PedidoProductService;
import com.prss6.sisgourmet.service.PedidoService;
import com.prss6.sisgourmet.service.ProductService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/pedido")
public class PedidoResource {

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ProductService productService;

	@Autowired
	private PedidoProductService pedidoProductService;

	@PostMapping
	public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {

		pedido.setStatus("Em Aberto");

		for (PedidoProduct pedidoProduto : pedido.getProducts()) {
			pedidoProduto.setPedido(pedido);

			productService.abaterQuantidade(pedidoProduto.getProduct().getId(), pedidoProduto.getQuantity());
			pedidoProductService.savePedidoProduct(pedidoProduto);
		}

		Pedido novoPedido = pedidoService.savePedido(pedido);

		return new ResponseEntity<>(novoPedido, HttpStatus.CREATED);
	}

	@GetMapping
	public List<Pedido> list() {
		return pedidoRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pedido> findById(@PathVariable Long id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		if (pedido.isPresent()) {
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
    public ResponseEntity<Pedido> updateStatus(@PathVariable Long id, @Valid @RequestBody Pedido pedido) {
        Pedido pedidoSaved = pedidoService.findPedidoById(id);

        pedidoSaved.setStatus(pedido.getStatus());

        Pedido pedidoUpdated = pedidoService.update(id, pedidoSaved);

        return ResponseEntity.ok(pedidoUpdated);
    }

}
