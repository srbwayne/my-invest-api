package com.carameldog.caramel_myinvest.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carameldog.caramel_myinvest.domain.Investment;
import com.carameldog.caramel_myinvest.repository.InvestmentRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/investment")
@RequiredArgsConstructor
public class InvestmentResource {
	
	private final InvestmentRepository investmentRepo;
	
	@GetMapping
	public ResponseEntity<?> getAll() {
		List<Investment> Investments = investmentRepo.findAll();
		return ResponseEntity.ok().body(Investments);
	}
	
	@GetMapping("/get/{code}")
	public ResponseEntity<?> getByCode(@PathVariable("code") Long code) {
		Optional<Investment> optional = investmentRepo.findById(code);
		if (optional.isPresent()) {
			return ResponseEntity.ok().body(optional.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/delete/{code}")
	public ResponseEntity<?> delete(@PathVariable("code") Long code) {
		try {
			investmentRepo.deleteById(code);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Investimento em uso.");
		}
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Investment Investment) {
		Investment = investmentRepo.save(Investment);
		return ResponseEntity.ok().body(Investment);
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Investment investment) {
		investment = investmentRepo.save(investment);
		return ResponseEntity.ok().body(investment);
	}
	
}
