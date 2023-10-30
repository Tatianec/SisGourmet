package com.prss6.sisgourmet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prss6.sisgourmet.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	 List<Product> findByIdIn(List<Long> ids);
}
