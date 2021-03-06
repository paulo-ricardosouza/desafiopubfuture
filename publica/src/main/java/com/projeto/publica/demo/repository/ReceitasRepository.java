package com.projeto.publica.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.publica.demo.enums.TipoReceita;
import com.projeto.publica.demo.model.Receitas;

@Repository
public interface ReceitasRepository extends JpaRepository<Receitas, Long>{

	List<Receitas> findByTipoReceita(TipoReceita tipoReceita);

	List<Receitas> findByDataRecebimentoEsperadoBetween(LocalDate dataInicial, LocalDate dataFinal);

}
