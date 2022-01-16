package com.projeto.publica.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.projeto.publica.demo.enums.TipoReceita;
import com.projeto.publica.demo.model.Receitas;
import com.projeto.publica.demo.repository.ReceitasRepository;

@Service
public class ReceitasService {

	@Autowired
	private ReceitasRepository receitasRepository;

	public List<Receitas> listar(){
		return receitasRepository.findAll();
	}

	public Receitas cadastrar(Receitas receitas) {
		return receitasRepository.save(receitas);
	}

	public void remover(Long id) {
		receitasRepository.deleteById(id);
	}

	public Receitas buscarReceita(Long id) {
		Optional<Receitas> receitasOpt = receitasRepository.findById(id);
		if(receitasOpt.isPresent()) {
			return receitasOpt.get();
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	public Receitas editar(Long id, Receitas receitas) {
		Receitas receita = buscarReceita(id);
		receita.setValor(receitas.getValor());
		receita.setDataRecebimento(receitas.getDataRecebimento());
		receita.setDataRecebimentoEsperado(receitas.getDataRecebimentoEsperado());
		receita.setDescricao(receitas.getDescricao());
		receita.setConta(receitas.getConta());
		receita.setTipoReceita(receitas.getTipoReceita());
		return receitasRepository.save(receita);
	}
	
	public List<Receitas> filtroPorTipoReceita(TipoReceita tipoReceita) {
		return receitasRepository.findByTipoReceita(tipoReceita);
	}
	
	public List<Receitas> filtroPorDataRecebimentoEsperado(LocalDate dataInicial, LocalDate dataFinal) {
		return receitasRepository.findByDataRecebimentoEsperadoBetween(dataInicial, dataFinal);
	}
}
