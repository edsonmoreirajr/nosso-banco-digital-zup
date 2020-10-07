package br.com.zup.nossobancodigitalzup.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zup.nossobancodigitalzup.domain.exception.in_use.CidadeEmUsoException;
import br.com.zup.nossobancodigitalzup.domain.exception.not_found.CidadeNaoEncontradaException;
import br.com.zup.nossobancodigitalzup.domain.model.Cidade;
import br.com.zup.nossobancodigitalzup.domain.model.Estado;
import br.com.zup.nossobancodigitalzup.domain.repository.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoService estadoService;
	
	public Page<Cidade> listAll(Pageable pageable) {
		return cidadeRepository.findAll(pageable);
	}
	
	@Transactional
	public Cidade save(Cidade cidade) {
		Long estadoId = cidade.getEstado().getEstadoId();
		Estado estado = estadoService.findById(estadoId);
		cidade.setEstado(estado);
		
		return cidadeRepository.save(cidade);
	}
	
	@Transactional
	public void remove(Long cidadeId) {
		try {
			cidadeRepository.deleteById(cidadeId);
			cidadeRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new CidadeNaoEncontradaException(cidadeId);
		
		} catch (DataIntegrityViolationException e) {
			throw new CidadeEmUsoException(cidadeId);
		}
	}
	
	public Cidade findById(Long cidadeId) {
		return cidadeRepository.findById(cidadeId)
			.orElseThrow(() -> new CidadeNaoEncontradaException(cidadeId));
	}
}
