package com.projeto.publica.demo.resource;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.publica.demo.enums.TipoDespesa;
import com.projeto.publica.demo.model.Despesas;
import com.projeto.publica.demo.service.DespesasService;

@RestController
@RequestMapping("/despesas")
public class DespesasResource {
	
	@Autowired
	private DespesasService despesasService;
	
	@GetMapping
	public List<Despesas> listarDespesas(){
		return despesasService.listar();
	}
	
	@GetMapping("/filtroPorTipoDespesa")
	public List<Despesas> filtroPorTipoDespesa(@RequestParam("tipoDespesa") TipoDespesa tipoDespesa) {
		return despesasService.filtroPorTipoDespesa(tipoDespesa);
	}
	
	@GetMapping("/filtroPorPeriodo")
	public List<Despesas> filtroPorPeriodo(@RequestParam("dataInicial") String dataInicial,
											   @RequestParam("dataFinal") String dataFinal) {
		LocalDate dataInicialLocalDate = LocalDate.parse(dataInicial, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate dataFinalLocalDate = LocalDate.parse(dataFinal, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		return despesasService.filtroPorDataPagamentoEsperado(dataInicialLocalDate, dataFinalLocalDate);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Despesas cadastrar(@RequestBody Despesas despesas) throws ParseException {
		return despesasService.cadastrar(despesas);
	}
	
	@DeleteMapping("/{id}")
	public void removerDespesa(@PathVariable Long id) {
		despesasService.remover(id);
	}
	
	@PutMapping("/{id}")
	public Despesas editar(@PathVariable Long id, @RequestBody Despesas despesas) throws ParseException {
		return despesasService.editar(id, despesas);
	}

}
