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

	public Pedido savePedido(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}

	public Pedido update(Long id, Pedido pedido) {
		Pedido pedidoSaved = findPedidoById(id);
		BeanUtils.copyProperties(pedido, pedidoSaved, "id");
		return pedidoRepository.save(pedidoSaved);
	}

	public Pedido findPedidoById(Long id) {
		return pedidoRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
	}
}
