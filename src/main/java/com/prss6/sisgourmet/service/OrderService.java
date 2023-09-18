package com.prss6.sisgourmet.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.prss6.sisgourmet.model.Pedido;
import com.prss6.sisgourmet.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository OrderRepository;
	
	public Pedido update(Long id, Pedido Order) {
		Pedido OrderSaved = findOrderById(id);
		BeanUtils.copyProperties(Order, OrderSaved, "id");
		return OrderRepository.save(OrderSaved);
	}

	public Pedido findOrderById(Long id) {
		Pedido OrderSaved = OrderRepository.findById(id)
				.orElseThrow(
				(() -> new EmptyResultDataAccessException(1)));
		return OrderSaved;
	}
	
}
