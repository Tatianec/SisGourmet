package com.prss6.sisgourmet.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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

import com.prss6.sisgourmet.model.Stock;
import com.prss6.sisgourmet.repository.StockRepository;
import com.prss6.sisgourmet.service.StockService;

@RestController
@RequestMapping("/stock")
public class StockResource {
	
	@Autowired
	private StockRepository stockRepository;
	
	@Autowired
	private StockService stockService;
	
	@GetMapping
	public List<Stock> list(){
		return stockRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Stock create(@Valid @RequestBody Stock stock, 
			HttpServletResponse response) {
		return stockRepository.save(stock);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Stock> findById(@PathVariable Long id){
		Optional<Stock> stock = stockRepository.findById(id);
		if(stock.isPresent()) {
			return ResponseEntity.ok(stock.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long id) {
		stockRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Stock> update(@PathVariable Long id,
			@Valid @RequestBody Stock stock){
		Stock stockSaved = stockService.update(id, stock);
		return ResponseEntity.ok(stockSaved);
	}
	
}
