package com.prss6.sisgourmet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prss6.sisgourmet.model.PedidoProduct;
import com.prss6.sisgourmet.repository.PedidoProductRepository;

@Service
public class PedidoProductService {

	@Autowired
	private PedidoProductRepository pedidoProductRepository;

	public PedidoProduct savePedidoProduct(PedidoProduct pedidoProduct) {
	    return pedidoProductRepository.save(pedidoProduct);
	}

	public PedidoProduct update(Long id, PedidoProduct pedidoProduct) {
		PedidoProduct pedidoProductSaved = findPedidoProductById(id);
		pedidoProductSaved.setQuantity(pedidoProduct.getQuantity());
		return pedidoProductRepository.save(pedidoProductSaved);
	}

	public PedidoProduct findPedidoProductById(Long id) {
		return pedidoProductRepository.findById(id).orElse(null);
	}

	public void deletePedidoProduct(Long id) {
		pedidoProductRepository.deleteById(id);
	}
}
