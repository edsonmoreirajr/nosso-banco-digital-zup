package br.com.zup.nossobancodigitalzup.api.v1_0.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.nossobancodigitalzup.api.ResourceUriHelper;
import br.com.zup.nossobancodigitalzup.api.v1_0.assembler.UsuarioInputDisassembler;
import br.com.zup.nossobancodigitalzup.api.v1_0.assembler.UsuarioModelAssembler;
import br.com.zup.nossobancodigitalzup.api.v1_0.model.UsuarioModel;
import br.com.zup.nossobancodigitalzup.api.v1_0.model.UsuarioModel;
import br.com.zup.nossobancodigitalzup.api.v1_0.model.input.UsuarioInput;
import br.com.zup.nossobancodigitalzup.domain.exception.DomainException;
import br.com.zup.nossobancodigitalzup.domain.exception.not_found.UsuarioNaoEncontradoException;
import br.com.zup.nossobancodigitalzup.domain.model.Usuario;
import br.com.zup.nossobancodigitalzup.domain.model.Usuario;
import br.com.zup.nossobancodigitalzup.domain.service.UsuarioService;

@RestController
@RequestMapping(path = "/v1/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioModelAssembler usuarioModelAssembler;
	
	@Autowired
	private UsuarioInputDisassembler usuarioInputDisassembler;
	
	@Autowired
	private PagedResourcesAssembler<Usuario> pagedResourcesAssembler;
	
	@GetMapping
	public PagedModel<UsuarioModel> listAll(@PageableDefault(size = 10) Pageable pageable) {
		Page<Usuario> usuariosPage = usuarioService.listAll(pageable);
		PagedModel<UsuarioModel> usuariosPagedModel = pagedResourcesAssembler
				.toModel(usuariosPage, usuarioModelAssembler);
		
		return usuariosPagedModel;
	}
	
	@GetMapping("/{clienteCpfCnpj}")
	public UsuarioModel findById(@PathVariable String clienteCpfCnpj) {
		Usuario usuario = usuarioService.findById(clienteCpfCnpj);
		return usuarioModelAssembler.toModel(usuario);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioModel add(@RequestBody @Valid UsuarioInput usuarioInput) {
		try {
			Usuario usuario = usuarioInputDisassembler.toDomainObject(usuarioInput);
			usuario = usuarioService.save(usuario);
			UsuarioModel usuarioModel = usuarioModelAssembler.toModel(usuario);
			ResourceUriHelper.addUriInResponseHeader(usuarioModel.getClienteCpfCnpj());
			
			return usuarioModel;
		} catch (UsuarioNaoEncontradoException e) {
			throw new DomainException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{usuarioId}")
	public UsuarioModel update(@PathVariable String clienteCpfCnpj,
			@RequestBody @Valid UsuarioInput usuarioInput) {
		try {
			Usuario usuarioAtual = usuarioService.findById(clienteCpfCnpj);
			usuarioInputDisassembler.copyToDomainObject(usuarioInput, usuarioAtual);
			usuarioAtual = usuarioService.save(usuarioAtual);
			
			return usuarioModelAssembler.toModel(usuarioAtual);
		} catch (UsuarioNaoEncontradoException e) {
			throw new DomainException(e.getMessage(), e);
		}
	}
	
	@DeleteMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable String clienteCpfCnpj) {
		usuarioService.remove(clienteCpfCnpj);	
	}
}
