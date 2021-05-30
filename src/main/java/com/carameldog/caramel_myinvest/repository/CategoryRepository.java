package com.carameldog.caramel_myinvest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carameldog.caramel_myinvest.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
}
