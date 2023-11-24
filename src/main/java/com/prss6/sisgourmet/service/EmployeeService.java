package com.prss6.sisgourmet.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.stereotype.Service;

import com.prss6.sisgourmet.model.Employee;
import com.prss6.sisgourmet.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;  

	public Employee update(Long id, Employee employee) {
		Employee employeeSaved = findEmployeeById(id);
		BeanUtils.copyProperties(employee, employeeSaved, "id");
		return employeeRepository.save(employeeSaved);
	}

	public Employee findEmployeeById(Long id) {
		return employeeRepository.findById(id)
				 .orElseThrow(() -> new EmptyResultDataAccessException(1));
	}

	public boolean login(String email, String password) {
	    Optional<Employee> employee = employeeRepository.findByEmail(email);

	    if (employee.isPresent()) {
	        System.out.println("Senha fornecida: " + password);
	        System.out.println("Senha armazenada: " + employee.get().getPassword());

	        boolean passwordMatches = passwordEncoder.matches(password, employee.get().getPassword());
	        System.out.println("Verificação de senha: " + passwordMatches);

	        if (passwordMatches) {
	            System.out.println("Login bem-sucedido para: " + email);
	        } else {
	            System.out.println("Falha no login para: " + email);
	        }

	        return passwordMatches;
	    } else {
	        System.out.println("Usuário não encontrado para o e-mail: " + email);
	        return false;
	    }
	}
	
}
