package com.projeto.publica.demo.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projeto.publica.demo.model.Contas;

@Repository
public interface ContasRepository extends JpaRepository<Contas, Long>{
	
	@Query("select sum(saldo) from Contas")
	BigDecimal findSaldoTotal();
	
}
