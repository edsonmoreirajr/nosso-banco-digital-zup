package br.com.zup.nossobancodigitalzup.api.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
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
import br.com.zup.nossobancodigitalzup.api.v1.assembler.ClienteInputDisassembler;
import br.com.zup.nossobancodigitalzup.api.v1.assembler.ClienteModelAssembler;
import br.com.zup.nossobancodigitalzup.api.v1.model.ClienteModel;
import br.com.zup.nossobancodigitalzup.api.v1.model.input.ClienteInput;
import br.com.zup.nossobancodigitalzup.domain.exception.DomainException;
import br.com.zup.nossobancodigitalzup.domain.exception.not_found.ClienteNaoEncontradoException;
import br.com.zup.nossobancodigitalzup.domain.model.Cliente;
import br.com.zup.nossobancodigitalzup.domain.service.ClienteService;

@RestController
@RequestMapping(path = "/v1/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ClienteModelAssembler clienteModelAssembler;
	
	@Autowired
	private ClienteInputDisassembler clienteInputDisassembler;
	
	@Autowired
	private PagedResourcesAssembler<Cliente> pagedResourcesAssembler;
	
	@GetMapping
	public PagedModel<ClienteModel> listAll(@PageableDefault(size = 10) Pageable pageable) {
		Page<Cliente> clientesPage = clienteService.listAll(pageable);
		PagedModel<ClienteModel> clientesPagedModel = pagedResourcesAssembler
				.toModel(clientesPage, clienteModelAssembler);
		
		return clientesPagedModel;
	}
	
	@GetMapping("/{clienteCpfCnpj}")
	public ClienteModel findById(@PathVariable String clienteCpfCnpj) {
		Cliente cliente = clienteService.findById(clienteCpfCnpj);
		return clienteModelAssembler.toModel(cliente);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteModel add(@RequestBody @Valid ClienteInput clienteInput) {
		try {
			Cliente cliente = clienteInputDisassembler.toDomainObject(clienteInput);
			cliente = clienteService.save(cliente);
			ClienteModel clienteModel = clienteModelAssembler.toModel(cliente);
			ResourceUriHelper.addUriInResponseHeader(clienteModel.getClienteCpfCnpj());
			
			return clienteModel;
		} catch (ClienteNaoEncontradoException e) {
			throw new DomainException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{clienteCpfCnpj}")
	public ClienteModel update(@PathVariable String clienteCpfCnpj,
			@RequestBody @Valid ClienteInput clienteInput) {
		try {
			Cliente clienteAtual = clienteService.findById(clienteCpfCnpj);
			clienteInputDisassembler.copyToDomainObject(clienteInput, clienteAtual);
			clienteAtual = clienteService.save(clienteAtual);
			
			return clienteModelAssembler.toModel(clienteAtual);
		} catch (ClienteNaoEncontradoException e) {
			throw new DomainException(e.getMessage(), e);
		}
	}
	
	@DeleteMapping("/{clienteCpfCnpj}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable String clienteCpfCnpj) {
		clienteService.remove(clienteCpfCnpj );	
	}
}
