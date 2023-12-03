package com.prss6.sisgourmet.service;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.prss6.sisgourmet.model.Product;
import com.prss6.sisgourmet.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product update(Long id, Product product) {
        Product productSaved = findProductById(id);
        BeanUtils.copyProperties(product, productSaved, "id");
        return productRepository.save(productSaved);
    }

    public Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public Product updateEstoque(Long id, Boolean estoque) {
        Product productSaved = findProductById(id);
        productSaved.setEstoque(estoque);
        return productRepository.save(productSaved);
    }

    @Transactional 
    public Product abaterQuantidade(Long id, Integer quantidade) {
        Product productSaved = findProductById(id);

        if (productSaved.getEstoque()) {
            if (hasSufficientStock(id, quantidade)) {
                int novaQuantidade = productSaved.getQtd_items() - quantidade;
                productSaved.setQtd_items(novaQuantidade);
                return productRepository.save(productSaved);
            } else {
                throw new RuntimeException("Quantidade insuficiente em estoque para o produto com ID " + id);
            }
        } else {
            productRepository.save(productSaved);
            return productSaved;
        }
    }



    public boolean hasSufficientStock(Long productId, int quantitySold) {
        Product product = findProductById(productId);
        return product.getEstoque() && product.getQtd_items() >= quantitySold;
    }
}
