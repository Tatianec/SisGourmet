package com.prss6.sisgourmet.model;

import java.util.List;

public class PedidoRequest {
	private Pedido pedido;
	private List<Long> productIds;

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<Long> getProductIds() {
		return productIds;
	}

	public void setProductIds(List<Long> productIds) {
		this.productIds = productIds;
	}

}
