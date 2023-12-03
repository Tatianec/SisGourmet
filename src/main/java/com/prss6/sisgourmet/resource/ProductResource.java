package com.prss6.sisgourmet.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.prss6.sisgourmet.model.Product;
import com.prss6.sisgourmet.repository.ProductRepository;
import com.prss6.sisgourmet.service.ProductService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/product")
public class ProductResource {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public List<Product> list(){
		return productRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Product create(@Valid @RequestBody Product product, 
			HttpServletResponse response) {
		return productRepository.save(product);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id){
		Optional<Product> product = productRepository.findById(id);
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
	public ResponseEntity<Product> update(@PathVariable Long id,
			@Valid @RequestBody Product product){
		Product productSaved = productService.update(id, product);
		return ResponseEntity.ok(productSaved);
	}
	
	@PatchMapping("/{id}/updateEstoque")
    public ResponseEntity<Product> updateEstoque(@PathVariable Long id,
                                                 @RequestParam Boolean estoque){
        Product productSaved = productService.updateEstoque(id, estoque);
        return ResponseEntity.ok(productSaved);
    }
	
	@PatchMapping("/{id}/updateQtdItems")
	public ResponseEntity<?> updateQtdItems(@PathVariable Long id,
	                                         @RequestParam Integer qtdItems){
	    try {
	        Product productSaved = productService.abaterQuantidade(id, qtdItems);
	        return ResponseEntity.ok(productSaved);
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao processar a solicitação.");
	    }
	}
}
