package com.prss6.sisgourmet.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.prss6.sisgourmet.model.Employee;
import com.prss6.sisgourmet.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee update(Long id, Employee Employee) {
		Employee EmployeeSaved = findEmployeeById(id);
		BeanUtils.copyProperties(Employee, EmployeeSaved, "id");
		return employeeRepository.save(EmployeeSaved);
	}

	public Employee findEmployeeById(Long id) {
		Employee EmployeeSaved = employeeRepository.findById(id)
				.orElseThrow(
				(() -> new EmptyResultDataAccessException(1)));
		return EmployeeSaved;
	}
	
}
