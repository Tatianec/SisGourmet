package com.prss6.sisgourmet.model;

import java.beans.Transient;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name = "order_date")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;
	
	@JsonProperty("employee_id")
	@Column(name = "employee_id")
	private Long employeeId;

	@JsonProperty("desk_id")
	@Column(name = "desk_id")
	private Long deskId;

	
	@ManyToMany
	@JoinTable(
	    name = "pedido_product",
	    joinColumns = @JoinColumn(name = "id_pedido"),
	    inverseJoinColumns = @JoinColumn(name = "id_product")
	)
	private List<Product> products;
		
	@NotNull
	private Double total;
	
	@NotNull
	@Size(max = 100)
	private String observation;
	
	
	@javax.persistence.Transient
	private List<Long> productIds;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	

	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Long getDeskId() {
		return deskId;
	}
	public void setDeskId(Long deskId) {
		this.deskId = deskId;
	}
	
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	
	
	public List<Long> getProductIds() {
		return productIds;
	}
	public void setProductIds(List<Long> productIds) {
		this.productIds = productIds;
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
		Pedido other = (Pedido) obj;
		return Objects.equals(id, other.id);
	}
}
