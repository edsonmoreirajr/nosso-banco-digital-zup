package br.com.zup.nossobancodigitalzup.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zup.nossobancodigitalzup.domain.exception.in_use.TransferenciaEmUsoException;
import br.com.zup.nossobancodigitalzup.domain.exception.not_found.TransferenciaNaoEncontradaException;
import br.com.zup.nossobancodigitalzup.domain.model.Conta;
import br.com.zup.nossobancodigitalzup.domain.model.Transferencia;
import br.com.zup.nossobancodigitalzup.domain.repository.TransferenciaRepository;

@Service
public class TransferenciaService {

	@Autowired
	private TransferenciaRepository trasnferenciaRepository;
	
	@Autowired
	private ContaService contaService;
	
	public Page<Transferencia> listAll(Pageable pageable) {
		return trasnferenciaRepository.findAll(pageable);
	}
	
	@Transactional
	public Transferencia save(Transferencia transferencia) {
		Long contaId = transferencia.getContaBean().getContaId();
		Conta conta = contaService.findById(contaId);
		transferencia.setContaBean(conta);
		
		return trasnferenciaRepository.save(transferencia);
	}
	
	@Transactional
	public void remove(Long trasnferenciaId) {
		try {
			trasnferenciaRepository.deleteById(trasnferenciaId);
			trasnferenciaRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new TransferenciaNaoEncontradaException(trasnferenciaId);
		
		} catch (DataIntegrityViolationException e) {
			throw new TransferenciaEmUsoException(trasnferenciaId);
		}
	}
	
	public Transferencia findById(Long trasnferenciaId) {
		return trasnferenciaRepository.findById(trasnferenciaId)
			.orElseThrow(() -> new TransferenciaNaoEncontradaException(trasnferenciaId));
	}
}
