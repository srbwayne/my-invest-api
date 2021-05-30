package com.carameldog.caramel_myinvest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carameldog.caramel_myinvest.domain.Investment;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {
	
}
