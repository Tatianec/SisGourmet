package com.prss6.sisgourmet.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.prss6.sisgourmet.model.Pedido;
import com.prss6.sisgourmet.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido update(Long id, Pedido Pedido) {
		Pedido PedidoSaved = findPedidoById(id);
		BeanUtils.copyProperties(Pedido, PedidoSaved, "id");
		return pedidoRepository.save(PedidoSaved);
	}

	public Pedido findPedidoById(Long id) {
		Pedido PedidoSaved = pedidoRepository.findById(id)
				.orElseThrow(
				(() -> new EmptyResultDataAccessException(1)));
		return PedidoSaved;
	}
}
