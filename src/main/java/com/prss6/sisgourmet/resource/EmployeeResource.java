package com.prss6.sisgourmet.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.prss6.sisgourmet.model.Employee;
import com.prss6.sisgourmet.model.LoginResponse;
import com.prss6.sisgourmet.repository.EmployeeRepository;
import com.prss6.sisgourmet.service.EmployeeService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/employees")
public class EmployeeResource {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;


	@PostMapping("/authenticate")
	public ResponseEntity<LoginResponse> login(@RequestBody Employee employeeRequest) {
	    System.out.println("Tentativa de Login - Email: " + employeeRequest.getEmail());

	    Optional<Employee> optionalEmployee = employeeRepository.findByEmail(employeeRequest.getEmail());

	    if (optionalEmployee.isPresent()) {
	        Employee storedEmployee = optionalEmployee.get();
	        boolean passwordMatch = passwordEncoder.matches(employeeRequest.getPassword(), storedEmployee.getPassword());

	        if (passwordMatch) {
	            System.out.println("Login bem-sucedido para: " + employeeRequest.getEmail());
	            return ResponseEntity.ok(new LoginResponse(true, "Login bem-sucedido!"));
	        } else {
	            System.out.println("Falha no login para: " + employeeRequest.getEmail());
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                    .body(new LoginResponse(false, "Email ou senha incorretos!"));
	        }
	    } else {
	        System.out.println("Falha no login para: " + employeeRequest.getEmail());
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                .body(new LoginResponse(false, "Email ou senha incorretos!"));
	    }
	}

	@GetMapping
	public List<Employee> list() {
		return employeeRepository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Employee create(@Valid @RequestBody Employee employee, HttpServletResponse response) {
		String encodedPassword = passwordEncoder.encode(employee.getPassword());
		employee.setPassword(encodedPassword);
		return employeeRepository.save(employee);

	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Employee> findById(@PathVariable Long id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if (employee.isPresent()) {
			return ResponseEntity.ok(employee.get());
		}
		return employeeRepository.findById(id)
		        .map(ResponseEntity::ok)
		        .orElse(ResponseEntity.notFound().build());

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long id) {
		employeeRepository.deleteById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Employee> update(@PathVariable Long id, @Valid @RequestBody Employee employee) {
	    Employee employeeSaved = employeeService.update(id, employee);
	    return ResponseEntity.ok(employeeSaved);
	}
}
