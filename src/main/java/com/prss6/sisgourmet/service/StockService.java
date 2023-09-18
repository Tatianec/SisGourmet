package com.prss6.sisgourmet.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.prss6.sisgourmet.model.Stock;
import com.prss6.sisgourmet.repository.StockRepository;

@Service
public class StockService {

	@Autowired
	private StockRepository stockRepository;
	
	public Stock update(Long id, Stock Stock) {
		Stock StockSaved = findStockById(id);
		BeanUtils.copyProperties(Stock, StockSaved, "id");
		return stockRepository.save(StockSaved);
	}

	public Stock findStockById(Long id) {
		Stock StockSaved = stockRepository.findById(id)
				.orElseThrow(
				(() -> new EmptyResultDataAccessException(1)));
		return StockSaved;
	}
}
