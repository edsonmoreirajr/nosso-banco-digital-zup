package br.com.zup.nossobancodigitalzup.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zup.nossobancodigitalzup.domain.exception.in_use.EnderecoEmUsoException;
import br.com.zup.nossobancodigitalzup.domain.exception.not_found.EnderecoNaoEncontradoException;
import br.com.zup.nossobancodigitalzup.domain.model.Endereco;
import br.com.zup.nossobancodigitalzup.domain.model.Bairro;
import br.com.zup.nossobancodigitalzup.domain.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private BairroService bairroService;
	
	public Page<Endereco> listAll(Pageable pageable) {
		return enderecoRepository.findAll(pageable);
	}
	
	@Transactional
	public Endereco save(Endereco endereco) {
		Long bairroId = endereco.getBairro().getBairroId();
		Bairro bairro = bairroService.findById(bairroId);
		endereco.setBairro(bairro);
		
		return enderecoRepository.save(endereco);
	}
	
	@Transactional
	public void remove(Long enderecoId) {
		try {
			enderecoRepository.deleteById(enderecoId);
			enderecoRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new EnderecoNaoEncontradoException(enderecoId);
		
		} catch (DataIntegrityViolationException e) {
			throw new EnderecoEmUsoException(enderecoId);
		}
	}
	
	public Endereco findById(Long enderecoId) {
		return enderecoRepository.findById(enderecoId)
			.orElseThrow(() -> new EnderecoNaoEncontradoException(enderecoId));
	}
}
