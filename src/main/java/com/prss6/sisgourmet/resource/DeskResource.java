package com.prss6.sisgourmet.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.prss6.sisgourmet.model.Desk;
import com.prss6.sisgourmet.repository.DeskRepository;
import com.prss6.sisgourmet.service.DeskService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/desk")
public class DeskResource {
	
	@Autowired
	private DeskRepository deskRepository;
	
	@Autowired
	private DeskService deskService;
	
	@GetMapping
	public List<Desk> list(){
		return deskRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Desk create(@Valid @RequestBody Desk desk, 
			HttpServletResponse response) {
		return deskRepository.save(desk);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Desk> findById(@PathVariable Long id){
		Optional<Desk> desk = deskRepository.findById(id);
		if(desk.isPresent()) {
			return ResponseEntity.ok(desk.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long id) {
		deskRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Desk> update(@PathVariable Long id,
			@Valid @RequestBody Desk desk){
		Desk deskSaved = deskService.update(id, desk);
		return ResponseEntity.ok(deskSaved);
	}

	@PostMapping("/{id}/reserve")
	public ResponseEntity<?> reserveDesk(@PathVariable Long id){
	    try {
	        Desk reservedDesk = deskService.reserveDesk(id);
	        return ResponseEntity.ok(reservedDesk);
	    } catch (IllegalStateException e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    }
	}

	@PostMapping("/{id}/release")
	public ResponseEntity<Desk> releaseDesk(@PathVariable Long id){
	    Desk releasedDesk = deskService.releaseDesk(id);
	    return ResponseEntity.ok(releasedDesk);
	}

	
}
