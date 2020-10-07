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
import br.com.zup.nossobancodigitalzup.api.v1_0.assembler.EstadoInputDisassembler;
import br.com.zup.nossobancodigitalzup.api.v1_0.assembler.EstadoModelAssembler;
import br.com.zup.nossobancodigitalzup.api.v1_0.model.EstadoModel;
import br.com.zup.nossobancodigitalzup.api.v1_0.model.EstadoModel;
import br.com.zup.nossobancodigitalzup.api.v1_0.model.input.EstadoInput;
import br.com.zup.nossobancodigitalzup.domain.exception.DomainException;
import br.com.zup.nossobancodigitalzup.domain.exception.not_found.EstadoNaoEncontradoException;
import br.com.zup.nossobancodigitalzup.domain.model.Estado;
import br.com.zup.nossobancodigitalzup.domain.model.Estado;
import br.com.zup.nossobancodigitalzup.domain.service.EstadoService;

@RestController
@RequestMapping(path = "/v1/estados", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController {

	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private EstadoModelAssembler estadoModelAssembler;
	
	@Autowired
	private EstadoInputDisassembler estadoInputDisassembler;
	
	@Autowired
	private PagedResourcesAssembler<Estado> pagedResourcesAssembler;
	
	@GetMapping
	public PagedModel<EstadoModel> listAll(@PageableDefault(size = 10) Pageable pageable) {
		Page<Estado> estadosPage = estadoService.listAll(pageable);
		PagedModel<EstadoModel> estadosPagedModel = pagedResourcesAssembler
				.toModel(estadosPage, estadoModelAssembler);
		
		return estadosPagedModel;
	}
	
	@GetMapping("/{estadoId}")
	public EstadoModel findById(@PathVariable Long estadoId) {
		Estado estado = estadoService.findById(estadoId);
		return estadoModelAssembler.toModel(estado);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoModel add(@RequestBody @Valid EstadoInput estadoInput) {
		try {
			Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);
			estado = estadoService.save(estado);
			EstadoModel estadoModel = estadoModelAssembler.toModel(estado);
			ResourceUriHelper.addUriInResponseHeader(estadoModel.getEstadoId());
			
			return estadoModel;
		} catch (EstadoNaoEncontradoException e) {
			throw new DomainException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{estadoId}")
	public EstadoModel update(@PathVariable Long estadoId,
			@RequestBody @Valid EstadoInput estadoInput) {
		try {
			Estado estadoAtual = estadoService.findById(estadoId);
			estadoInputDisassembler.copyToDomainObject(estadoInput, estadoAtual);
			estadoAtual = estadoService.save(estadoAtual);
			
			return estadoModelAssembler.toModel(estadoAtual);
		} catch (EstadoNaoEncontradoException e) {
			throw new DomainException(e.getMessage(), e);
		}
	}
	
	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long estadoId) {
		estadoService.remove(estadoId );	
	}
}
