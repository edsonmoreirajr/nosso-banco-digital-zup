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
import br.com.zup.nossobancodigitalzup.api.v1.assembler.TransferenciaInputDisassembler;
import br.com.zup.nossobancodigitalzup.api.v1.assembler.TransferenciaModelAssembler;
import br.com.zup.nossobancodigitalzup.api.v1.model.TransferenciaModel;
import br.com.zup.nossobancodigitalzup.api.v1.model.input.TransferenciaInput;
import br.com.zup.nossobancodigitalzup.domain.exception.DomainException;
import br.com.zup.nossobancodigitalzup.domain.exception.not_found.TransferenciaNaoEncontradaException;
import br.com.zup.nossobancodigitalzup.domain.model.Transferencia;
import br.com.zup.nossobancodigitalzup.domain.service.TransferenciaService;

@RestController
@RequestMapping(path = "/v1/transferencias", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransferenciaController {

	@Autowired
	private TransferenciaService transferenciaService;
	
	@Autowired
	private TransferenciaModelAssembler transferenciaModelAssembler;
	
	@Autowired
	private TransferenciaInputDisassembler transferenciaInputDisassembler;
	
	@Autowired
	private PagedResourcesAssembler<Transferencia> pagedResourcesAssembler;
	
	@GetMapping
	public PagedModel<TransferenciaModel> listAll(@PageableDefault(size = 10) Pageable pageable) {
		Page<Transferencia> transferenciasPage = transferenciaService.listAll(pageable);
		PagedModel<TransferenciaModel> transferenciasPagedModel = pagedResourcesAssembler
				.toModel(transferenciasPage, transferenciaModelAssembler);
		
		return transferenciasPagedModel;
	}
	
	@GetMapping("/{transferenciaId}")
	public TransferenciaModel findById(@PathVariable Long transferenciaId) {
		Transferencia transferencia = transferenciaService.findById(transferenciaId);
		return transferenciaModelAssembler.toModel(transferencia);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TransferenciaModel add(@RequestBody @Valid TransferenciaInput transferenciaInput) {
		try {
			Transferencia transferencia = transferenciaInputDisassembler.toDomainObject(transferenciaInput);
			transferencia = transferenciaService.save(transferencia);
			TransferenciaModel transferenciaModel = transferenciaModelAssembler.toModel(transferencia);
			ResourceUriHelper.addUriInResponseHeader(transferenciaModel.getTransferenciaId());
			
			return transferenciaModel;
		} catch (TransferenciaNaoEncontradaException e) {
			throw new DomainException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{transferenciaId}")
	public TransferenciaModel update(@PathVariable Long transferenciaId,
			@RequestBody @Valid TransferenciaInput transferenciaInput) {
		try {
			Transferencia transferenciaAtual = transferenciaService.findById(transferenciaId);
			transferenciaInputDisassembler.copyToDomainObject(transferenciaInput, transferenciaAtual);
			transferenciaAtual = transferenciaService.save(transferenciaAtual);
			
			return transferenciaModelAssembler.toModel(transferenciaAtual);
		} catch (TransferenciaNaoEncontradaException e) {
			throw new DomainException(e.getMessage(), e);
		}
	}
	
	@DeleteMapping("/{transferenciaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long transferenciaId) {
		transferenciaService.remove(transferenciaId );	
	}
}
