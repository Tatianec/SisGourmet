package com.prss6.sisgourmet.model;

import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name = "order_date")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;
	
	@NotNull
	@JoinColumn(name = "employee_id")
	private Employee employee_id;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "desk_id")
	private Desk desk;
	
	@NotNull
	private Double total;
	
	@NotNull
	@Size(max = 100)
	private String observation;
	
	
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

	public Employee getId_employee() {
		return employee_id;
	}
	public void setId_employee(Employee employee_id) {
		this.employee_id = employee_id;
	}

	
	public Desk getTable() {
		return desk;
	}
	public void setTable(Desk desk) {
		this.desk = desk;
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
