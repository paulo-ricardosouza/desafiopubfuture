package com.projeto.publica.demo.resource;

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

import com.projeto.publica.demo.enums.TipoReceita;
import com.projeto.publica.demo.model.Receitas;
import com.projeto.publica.demo.service.ReceitasService;

@RestController
@RequestMapping("/receitas")
public class ReceitasResource {
	
	@Autowired
	private ReceitasService receitasService;
	
	@GetMapping
	public List<Receitas> listarReceitas(){
		return receitasService.listar();
	}
	
	@GetMapping("/filtroPorTipoReceita")
	public List<Receitas> filtroPorTipoReceita(@RequestParam("tipoReceita") TipoReceita tipoReceita) {
		return receitasService.filtroPorTipoReceita(tipoReceita);
	}
	
	@GetMapping("/filtroPorDataRecebimentoEsperado")
	public List<Receitas> filtroPorPeriodo(@RequestParam("dataInicial") String dataInicial,
											   @RequestParam("dataFinal") String dataFinal) {
		LocalDate dataInicialLocalDate = LocalDate.parse(dataInicial, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate dataFinalLocalDate = LocalDate.parse(dataFinal, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		return receitasService.filtroPorDataRecebimentoEsperado(dataInicialLocalDate, dataFinalLocalDate);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Receitas cadastrar(@RequestBody Receitas receitas) {
		return receitasService.cadastrar(receitas);
	}
	
	@DeleteMapping("/{id}")
	public void removerReceita(@PathVariable Long id) {
		receitasService.remover(id);
	}
	
	@PutMapping("/{id}")
	public Receitas editar(@PathVariable Long id, @RequestBody Receitas receitas ) {
		return receitasService.editar(id, receitas);
	}
}
