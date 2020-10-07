package br.com.zup.nossobancodigitalzup.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zup.nossobancodigitalzup.domain.exception.in_use.ClienteEmUsoException;
import br.com.zup.nossobancodigitalzup.domain.exception.not_found.ClienteNaoEncontradoException;
import br.com.zup.nossobancodigitalzup.domain.model.Cliente;
import br.com.zup.nossobancodigitalzup.domain.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	
	public Page<Cliente> listAll(Pageable pageable) {
		return clienteRepository.findAll(pageable);
	}
	
	@Transactional
	public Cliente save(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public void remove(String cpfCnpj) {
		try {
			clienteRepository.deleteById(cpfCnpj);
			clienteRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new ClienteNaoEncontradoException(cpfCnpj);
		
		} catch (DataIntegrityViolationException e) {
			throw new ClienteEmUsoException(cpfCnpj);
		}
	}
	
	public Cliente findById(String cpfCnpj) {
		return clienteRepository.findById(cpfCnpj)
			.orElseThrow(() -> new ClienteNaoEncontradoException(cpfCnpj));
	}
}
