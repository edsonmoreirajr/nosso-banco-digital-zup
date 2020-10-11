package br.com.zup.nossobancodigitalzup.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zup.nossobancodigitalzup.domain.exception.in_use.PropostaEmUsoException;
import br.com.zup.nossobancodigitalzup.domain.exception.not_found.PropostaNaoEncontradaException;
import br.com.zup.nossobancodigitalzup.domain.model.Proposta;
import br.com.zup.nossobancodigitalzup.domain.model.Cliente;
import br.com.zup.nossobancodigitalzup.domain.repository.PropostaRepository;

@Service
public class PropostaService {

	@Autowired
	private PropostaRepository propostaRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	public Page<Proposta> listAll(Pageable pageable) {
		return propostaRepository.findAll(pageable);
	}
	
	@Transactional
	public Proposta save(Proposta proposta) {
		String cpfCnpj = proposta.getCliente().getCpfCnpj();
		Cliente cliente = clienteService.findById(cpfCnpj);
		proposta.setCliente(cliente);
		
		return propostaRepository.save(proposta);
	}
	
	@Transactional
	public void remove(Long propostaId) {
		try {
			propostaRepository.deleteById(propostaId);
			propostaRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new PropostaNaoEncontradaException(propostaId);
		
		} catch (DataIntegrityViolationException e) {
			throw new PropostaEmUsoException(propostaId);
		}
	}
	
	public Proposta findById(Long propostaId) {
		return propostaRepository.findById(propostaId)
			.orElseThrow(() -> new PropostaNaoEncontradaException(propostaId));
	}
}
