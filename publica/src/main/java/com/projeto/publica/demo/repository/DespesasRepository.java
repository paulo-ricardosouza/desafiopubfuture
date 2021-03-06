package com.projeto.publica.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.publica.demo.enums.TipoDespesa;
import com.projeto.publica.demo.model.Despesas;

@Repository
public interface DespesasRepository extends JpaRepository<Despesas, Long>{

	List<Despesas> findByTipoDespesa(TipoDespesa tipoDespesa);
	
	List<Despesas> findByDataPagamentoEsperadoBetween(LocalDate dataInicial, LocalDate dataFinal);
}
