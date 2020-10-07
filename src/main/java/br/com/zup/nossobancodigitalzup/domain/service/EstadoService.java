package br.com.zup.nossobancodigitalzup.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zup.nossobancodigitalzup.domain.exception.not_found.EstadoNaoEncontradoException;
import br.com.zup.nossobancodigitalzup.domain.model.Estado;
import br.com.zup.nossobancodigitalzup.domain.repository.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;
	
	public Page<Estado> listAll(Pageable pageable) {
		return estadoRepository.findAll(pageable);
	}
	
	@Transactional
	public Estado save(Estado estado) {
		return estadoRepository.save(estado);
	}
	
	@Transactional
	public void remove(Long estadoId) {
		try {
			estadoRepository.deleteById(estadoId);
			estadoRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontradoException(estadoId);
		
		} catch (DataIntegrityViolationException e) {
			throw new EstadoNaoEncontradoException(estadoId);
		}
	}

	public Estado findById(Long estadoId) {
		return estadoRepository.findById(estadoId)
			.orElseThrow(() -> new EstadoNaoEncontradoException(estadoId));
	}
}
