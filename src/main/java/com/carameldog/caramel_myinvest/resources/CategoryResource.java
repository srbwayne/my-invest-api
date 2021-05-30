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

import com.carameldog.caramel_myinvest.domain.Category;
import com.carameldog.caramel_myinvest.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryResource {
	
	private final CategoryRepository categoryRepo;
	
	@GetMapping
	public ResponseEntity<?> getAll() {
		List<Category> categorys = categoryRepo.findAll();
		return ResponseEntity.ok().body(categorys);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getByCode(@PathVariable("id") Long id) {
		Optional<Category> optional = categoryRepo.findById(id);
		if (optional.isPresent()) {
			return ResponseEntity.ok().body(optional.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/delete/{code}")
	public ResponseEntity<?> delete(@PathVariable("code") Long code) {
		try {
			categoryRepo.deleteById(code);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Categoria em uso.");
		}
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Category category) {
		category = categoryRepo.save(category);
		return ResponseEntity.ok().body(category);
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Category category) {
		category = categoryRepo.save(category);
		return ResponseEntity.ok().body(category);
	}
}
