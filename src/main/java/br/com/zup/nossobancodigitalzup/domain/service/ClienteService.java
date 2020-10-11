package br.com.zup.nossobancodigitalzup.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zup.nossobancodigitalzup.domain.exception.duplicity.CpfCnpjDuplicadoException;
import br.com.zup.nossobancodigitalzup.domain.exception.duplicity.CpfCnpjEmailDuplicadosException;
import br.com.zup.nossobancodigitalzup.domain.exception.duplicity.EmailDuplicadoException;
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
		clienteRepository.detach(cliente);

		Optional<Cliente> clienteExistente = clienteRepository.findByCpfCnpjOrEmail(cliente.getCpfCnpj(),cliente.getEmail());
		//Verifica se o cpf,cnpj ou email do cliente já está cadastrado no sistema
		
		if (clienteExistente.isPresent()) {
			String cpfExistente = clienteExistente.get().getCpfCnpj();
			String emailExistente = clienteExistente.get().getEmail();

			if (cliente.getCpfCnpj().equals(cpfExistente) && cliente.getEmail().equals(emailExistente)) {
				throw new CpfCnpjEmailDuplicadosException(cpfExistente, emailExistente);

			} else if (cliente.getCpfCnpj().equals(cpfExistente)) {
				throw new CpfCnpjDuplicadoException(cpfExistente);

			} else if (cliente.getEmail().equals(emailExistente)) {
				throw new EmailDuplicadoException(emailExistente);
			}
		}

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
		return clienteRepository.findById(cpfCnpj).orElseThrow(() -> new ClienteNaoEncontradoException(cpfCnpj));
	}
}
