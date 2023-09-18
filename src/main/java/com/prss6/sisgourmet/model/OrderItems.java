package com.prss6.sisgourmet.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "order-items")
public class OrderItems {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private int qtd_items;
	
	@NotNull
	@JoinColumn(name = "id_order")
	private Pedido id_order;

	@NotNull
	@JoinColumn(name = "id_product")
	private Product id_product;
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	
	
	public int getQtd_items() {
		return qtd_items;
	}
	public void setQtd_items(int qtd_items) {
		this.qtd_items = qtd_items;
	}
	public Pedido getId_order() {
		return id_order;
	}
	public void setId_order(Pedido id_order) {
		this.id_order = id_order;
	}
	public Product getId_product() {
		return id_product;
	}
	public void setId_product(Product id_product) {
		this.id_product = id_product;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItems other = (OrderItems) obj;
		return Objects.equals(id, other.id);
	}
}
