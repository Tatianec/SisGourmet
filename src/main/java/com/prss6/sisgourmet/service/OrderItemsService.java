package com.prss6.sisgourmet.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.prss6.sisgourmet.model.OrderItems;
import com.prss6.sisgourmet.repository.OrderItemsRepository;

@Service
public class OrderItemsService {

	@Autowired
	private OrderItemsRepository OrderItemsRepository;
	
	public OrderItems update(Long id, OrderItems OrderItems) {
		OrderItems OrderItemsSaved = findOrderItemsById(id);
		BeanUtils.copyProperties(OrderItems, OrderItemsSaved, "id");
		return OrderItemsRepository.save(OrderItemsSaved);
	}

	public OrderItems findOrderItemsById(Long id) {
		OrderItems OrderItemsSaved = OrderItemsRepository.findById(id)
				.orElseThrow(
				(() -> new EmptyResultDataAccessException(1)));
		return OrderItemsSaved;
	}
	
}
