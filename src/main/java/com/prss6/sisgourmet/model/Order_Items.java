package com.prss6.sisgourmet.model;

import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Order_Items {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name = "order_date")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee id_employee;
	
	@NotNull
	private int table;
	
	@NotNull
	private Double value;
	
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
		return id_employee;
	}
	public void setId_employee(Employee id_employee) {
		this.id_employee = id_employee;
	}
	public int getTable() {
		return table;
	}
	public void setTable(int table) {
		this.table = table;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
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
		Order_Items other = (Order_Items) obj;
		return Objects.equals(id, other.id);
	}
}
