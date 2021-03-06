package com.projeto.publica.demo.resource;

import java.math.BigDecimal;
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

import com.projeto.publica.demo.model.Contas;
import com.projeto.publica.demo.service.ContasService;

@RestController
@RequestMapping("/contas")
public class ContasResource {

	@Autowired
	private ContasService contasService;

	@GetMapping
	public List<Contas> listarContas() {
		return contasService.listar();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Contas cadastrar(@RequestBody Contas contas) {
		return contasService.cadastrar(contas);
	}

	@DeleteMapping("/{id}")
	public void removerConta(@PathVariable Long id) {
		contasService.remover(id);
	}

	@PutMapping("/{id}")
	public Contas editar(@PathVariable Long id, @RequestBody Contas contas) {
		return contasService.editar(id, contas);
	}

	@GetMapping("/listarSaldoTotal")
	public BigDecimal listarSaldoTotal() {
		return contasService.listarSaldoTotal();
	}
	
	@GetMapping("/transferirSaldo")
	public void transferirSaldo(@RequestParam("idContaRemetente") Long idContaRemetente,
								@RequestParam("idContaDestino") Long idContaDestino,
								@RequestParam("valorTransferencia") double valorTransferencia) {
		contasService.transferirSaldo(idContaRemetente, idContaDestino, valorTransferencia);
	}

}
