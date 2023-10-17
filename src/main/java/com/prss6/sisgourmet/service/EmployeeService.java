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

	public Employee update(Long id, Employee employee) {
		Employee employeeSaved = findEmployeeById(id);
		BeanUtils.copyProperties(employee, employeeSaved, "id");
		return employeeRepository.save(employeeSaved);
	}

	public Employee findEmployeeById(Long id) {
		return employeeRepository.findById(id)
				 .orElseThrow(() -> new EmptyResultDataAccessException(1));
	}
}

