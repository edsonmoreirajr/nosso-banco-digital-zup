package br.com.zup.nossobancodigitalzup.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zup.nossobancodigitalzup.domain.exception.in_use.ContaEmUsoException;
import br.com.zup.nossobancodigitalzup.domain.exception.not_found.ContaNaoEncontradaException;
import br.com.zup.nossobancodigitalzup.domain.model.Conta;
import br.com.zup.nossobancodigitalzup.domain.model.Proposta;
import br.com.zup.nossobancodigitalzup.domain.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private PropostaService propostaService;
	
	public Page<Conta> listAll(Pageable pageable) {
		return contaRepository.findAll(pageable);
	}
	
	@Transactional
	public Conta save(Conta conta) {
		Long propostaId = conta.getProposta().getPropostaId();
		Proposta proposta = propostaService.findById(propostaId);
		conta.setProposta(proposta);
		
		return contaRepository.save(conta);
	}
	
	@Transactional
	public void remove(Long contaId) {
		try {
			contaRepository.deleteById(contaId);
			contaRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new ContaNaoEncontradaException(contaId);
		
		} catch (DataIntegrityViolationException e) {
			throw new ContaEmUsoException(contaId);
		}
	}
	
	public Conta findById(Long contaId) {
		return contaRepository.findById(contaId)
			.orElseThrow(() -> new ContaNaoEncontradaException(contaId));
	}
}
