package br.com.zup.nossobancodigitalzup.api.v1_0.controller;

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
import br.com.zup.nossobancodigitalzup.api.v1_0.assembler.CidadeInputDisassembler;
import br.com.zup.nossobancodigitalzup.api.v1_0.assembler.CidadeModelAssembler;
import br.com.zup.nossobancodigitalzup.api.v1_0.model.CidadeModel;
import br.com.zup.nossobancodigitalzup.api.v1_0.model.input.CidadeInput;
import br.com.zup.nossobancodigitalzup.domain.exception.DomainException;
import br.com.zup.nossobancodigitalzup.domain.exception.not_found.CidadeNaoEncontradaException;
import br.com.zup.nossobancodigitalzup.domain.model.Cidade;
import br.com.zup.nossobancodigitalzup.domain.service.CidadeService;

@RestController
@RequestMapping(path = "/v1/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController {

	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private CidadeModelAssembler cidadeModelAssembler;
	
	@Autowired
	private CidadeInputDisassembler cidadeInputDisassembler;
	
	@Autowired
	private PagedResourcesAssembler<Cidade> pagedResourcesAssembler;
	
	@GetMapping
	public PagedModel<CidadeModel> listAll(@PageableDefault(size = 10) Pageable pageable) {
		Page<Cidade> cidadesPage = cidadeService.listAll(pageable);
		PagedModel<CidadeModel> cidadesPagedModel = pagedResourcesAssembler
				.toModel(cidadesPage, cidadeModelAssembler);
		
		return cidadesPagedModel;
	}
	
	@GetMapping("/{cidadeId}")
	public CidadeModel findById(@PathVariable Long cidadeId) {
		Cidade cidade = cidadeService.findById(cidadeId);
		return cidadeModelAssembler.toModel(cidade);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeModel add(@RequestBody @Valid CidadeInput cidadeInput) {
		try {
			Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);
			cidade = cidadeService.save(cidade);
			CidadeModel cidadeModel = cidadeModelAssembler.toModel(cidade);
			ResourceUriHelper.addUriInResponseHeader(cidadeModel.getCidadeId());
			
			return cidadeModel;
		} catch (CidadeNaoEncontradaException e) {
			throw new DomainException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{cidadeId}")
	public CidadeModel update(@PathVariable Long cidadeId,
			@RequestBody @Valid CidadeInput cidadeInput) {
		try {
			Cidade cidadeAtual = cidadeService.findById(cidadeId);
			cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);
			cidadeAtual = cidadeService.save(cidadeAtual);
			
			return cidadeModelAssembler.toModel(cidadeAtual);
		} catch (CidadeNaoEncontradaException e) {
			throw new DomainException(e.getMessage(), e);
		}
	}
	
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long cidadeId) {
		cidadeService.remove(cidadeId );	
	}
}
