package com.projeto.publica.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.projeto.publica.demo.enums.TipoDespesa;
import com.projeto.publica.demo.model.Despesas;
import com.projeto.publica.demo.repository.DespesasRepository;

@Service
public class DespesasService {

	@Autowired
	private DespesasRepository despesasRepository;

	public List<Despesas> listar(){
		return despesasRepository.findAll();
	}

	public Despesas cadastrar(Despesas despesas) {
		return despesasRepository.save(despesas);
	}

	public void remover(Long id) {
		despesasRepository.deleteById(id);
	}

	public Despesas buscarDespesa(Long id) {
		Optional<Despesas> despesasOpt = despesasRepository.findById(id);
		if(despesasOpt.isPresent()) {
			return despesasOpt.get();
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	public Despesas editar(Long id, Despesas despesas) {
		Despesas despesa = buscarDespesa(id);
		despesa.setValor(despesas.getValor());
		despesa.setDataPagamento(despesas.getDataPagamento());
		despesa.setDataPagamentoEsperado(despesas.getDataPagamentoEsperado());
		despesa.setTipoDespesa(despesas.getTipoDespesa());
		despesa.setConta(despesas.getConta());
		return despesasRepository.save(despesa);
	}

	public List<Despesas> filtroPorTipoDespesa(TipoDespesa tipoDespesa) {
		return despesasRepository.findByTipoDespesa(tipoDespesa);
	}
	
	public List<Despesas> filtroPorDataPagamentoEsperado(LocalDate dataInicial, LocalDate dataFinal) {
		return despesasRepository.findByDataPagamentoEsperadoBetween(dataInicial, dataFinal);
	}
}
