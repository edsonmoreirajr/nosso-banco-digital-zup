package br.com.zup.nossobancodigitalzup.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zup.nossobancodigitalzup.domain.exception.in_use.BairroEmUsoException;
import br.com.zup.nossobancodigitalzup.domain.exception.not_found.BairroNaoEncontradoException;
import br.com.zup.nossobancodigitalzup.domain.model.Bairro;
import br.com.zup.nossobancodigitalzup.domain.model.Cidade;
import br.com.zup.nossobancodigitalzup.domain.repository.BairroRepository;

@Service
public class BairroService {

	@Autowired
	private BairroRepository bairroRepository;
	
	@Autowired
	private CidadeService cidadeService;
	
	public Page<Bairro> listAll(Pageable pageable) {
		return bairroRepository.findAll(pageable);
	}
	
	@Transactional
	public Bairro save(Bairro bairro) {
		Long cidadeId = bairro.getCidade().getCidadeId();
		Cidade cidade = cidadeService.findById(cidadeId);
		bairro.setCidade(cidade);
		
		return bairroRepository.save(bairro);
	}
	
	@Transactional
	public void remove(Long bairroId) {
		try {
			bairroRepository.deleteById(bairroId);
			bairroRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new BairroNaoEncontradoException(bairroId);
		
		} catch (DataIntegrityViolationException e) {
			throw new BairroEmUsoException(bairroId);
		}
	}
	
	public Bairro findById(Long bairroId) {
		return bairroRepository.findById(bairroId)
			.orElseThrow(() -> new BairroNaoEncontradoException(bairroId));
	}
	
}
