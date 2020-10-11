package br.com.zup.nossobancodigitalzup.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zup.nossobancodigitalzup.domain.exception.in_use.UsuarioEmUsoException;
import br.com.zup.nossobancodigitalzup.domain.exception.not_found.UsuarioNaoEncontradoException;
import br.com.zup.nossobancodigitalzup.domain.model.Cliente;
import br.com.zup.nossobancodigitalzup.domain.model.Usuario;
import br.com.zup.nossobancodigitalzup.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	public Page<Usuario> listAll(Pageable pageable) {
		return usuarioRepository.findAll(pageable);
	}
	
	@Transactional
	public Usuario save(Usuario usuario) {
		String cpfCnpj = usuario.getCliente().getCpfCnpj();
		Cliente cliente = clienteService.findById(cpfCnpj);
		usuario.setCliente(cliente);
		
		return usuarioRepository.save(usuario);
	}
	
	@Transactional
	public void remove(String cpfCnpj) {
		try {
			usuarioRepository.deleteById(cpfCnpj);
			usuarioRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new UsuarioNaoEncontradoException(cpfCnpj);
		
		} catch (DataIntegrityViolationException e) {
			throw new UsuarioEmUsoException(cpfCnpj);
		}
	}
	
	public Usuario findById(String cpfCnpj) {
		return usuarioRepository.findById(cpfCnpj)
			.orElseThrow(() -> new UsuarioNaoEncontradoException(cpfCnpj));
	}
}
