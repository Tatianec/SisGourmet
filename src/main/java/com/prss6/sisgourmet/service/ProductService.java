package com.prss6.sisgourmet.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.prss6.sisgourmet.model.Product;
import com.prss6.sisgourmet.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public Product update(Long id, Product Product) {
		Product ProductSaved = findProductById(id);
		BeanUtils.copyProperties(Product, ProductSaved, "id");
		return productRepository.save(ProductSaved);
	}

	public Product findProductById(Long id) {
		Product ProductSaved = productRepository.findById(id)
				.orElseThrow(
				(() -> new EmptyResultDataAccessException(1)));
		return ProductSaved;
	}
}
