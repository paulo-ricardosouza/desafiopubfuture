package com.projeto.publica.demo.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.projeto.publica.demo.model.Contas;
import com.projeto.publica.demo.repository.ContasRepository;

@Service
public class ContasService {

	@Autowired
	private ContasRepository contasRepository;

	public List<Contas> listar(){
		return contasRepository.findAll();
	}

	public Contas cadastrar(Contas contas) {
		return contasRepository.save(contas);
	}

	public void remover(Long id) {
		contasRepository.deleteById(id);
	}

	public Contas buscarConta(Long id) {
		Optional<Contas> contasOpt = contasRepository.findById(id);
		if(contasOpt.isPresent()) {
			return contasOpt.get();
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	public Contas editar(Long id, Contas contas) {
		Contas conta = buscarConta(id);
		conta.setInstituicaoFinanceira(contas.getInstituicaoFinanceira());
		conta.setSaldo(contas.getSaldo());
		conta.setTipoConta(contas.getTipoConta());
		return contasRepository.save(conta);
	}
	
	public BigDecimal listarSaldoTotal() {
		return contasRepository.findSaldoTotal();
	}
	
	public void transferirSaldo(Long idContaRemetente, Long idContaDestino, double valorTransferencia) {
		Contas contaRemetente = buscarConta(idContaRemetente);
		Contas contaDestino = buscarConta(idContaDestino);
		
		double saldoRemetente = diminuirValorSaldo(contaRemetente.getSaldo(), valorTransferencia);
		double saldoDestino = aumentarValorSaldo(contaDestino.getSaldo(), valorTransferencia);
		
		contaRemetente.setSaldo(saldoRemetente);
		contaDestino.setSaldo(saldoDestino);
		
		contasRepository.save(contaRemetente);
		contasRepository.save(contaDestino);
	}
	
	private double diminuirValorSaldo(double saldo, double valor) {
		return saldo - valor;
	}
	
	private double aumentarValorSaldo(double saldo, double valor) {
		return saldo + valor;
	}
}
