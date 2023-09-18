package com.prss6.sisgourmet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prss6.sisgourmet.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	public Optional<Employee> findByEmail(String email);



}
