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
import br.com.zup.nossobancodigitalzup.api.v1_0.assembler.PropostaInputDisassembler;
import br.com.zup.nossobancodigitalzup.api.v1_0.assembler.PropostaModelAssembler;
import br.com.zup.nossobancodigitalzup.api.v1_0.model.PropostaModel;
import br.com.zup.nossobancodigitalzup.api.v1_0.model.PropostaModel;
import br.com.zup.nossobancodigitalzup.api.v1_0.model.input.PropostaInput;
import br.com.zup.nossobancodigitalzup.domain.exception.DomainException;
import br.com.zup.nossobancodigitalzup.domain.exception.not_found.PropostaNaoEncontradaException;
import br.com.zup.nossobancodigitalzup.domain.model.Proposta;
import br.com.zup.nossobancodigitalzup.domain.model.Proposta;
import br.com.zup.nossobancodigitalzup.domain.service.PropostaService;

@RestController
@RequestMapping(path = "/v1/propostas", produces = MediaType.APPLICATION_JSON_VALUE)
public class PropostaController {

	@Autowired
	private PropostaService propostaService;
	
	@Autowired
	private PropostaModelAssembler propostaModelAssembler;
	
	@Autowired
	private PropostaInputDisassembler propostaInputDisassembler;
	
	@Autowired
	private PagedResourcesAssembler<Proposta> pagedResourcesAssembler;
	
	@GetMapping
	public PagedModel<PropostaModel> listAll(@PageableDefault(size = 10) Pageable pageable) {
		Page<Proposta> propostasPage = propostaService.listAll(pageable);
		PagedModel<PropostaModel> propostasPagedModel = pagedResourcesAssembler
				.toModel(propostasPage, propostaModelAssembler);
		
		return propostasPagedModel;
	}
	
	@GetMapping("/{propostaId}")
	public PropostaModel findById(@PathVariable Long propostaId) {
		Proposta proposta = propostaService.findById(propostaId);
		return propostaModelAssembler.toModel(proposta);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PropostaModel add(@RequestBody @Valid PropostaInput propostaInput) {
		try {
			Proposta proposta = propostaInputDisassembler.toDomainObject(propostaInput);
			proposta = propostaService.save(proposta);
			PropostaModel propostaModel = propostaModelAssembler.toModel(proposta);
			ResourceUriHelper.addUriInResponseHeader(propostaModel.getPropostaId());
			
			return propostaModel;
		} catch (PropostaNaoEncontradaException e) {
			throw new DomainException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{propostaId}")
	public PropostaModel update(@PathVariable Long propostaId,
			@RequestBody @Valid PropostaInput propostaInput) {
		try {
			Proposta propostaAtual = propostaService.findById(propostaId);
			propostaInputDisassembler.copyToDomainObject(propostaInput, propostaAtual);
			propostaAtual = propostaService.save(propostaAtual);
			
			return propostaModelAssembler.toModel(propostaAtual);
		} catch (PropostaNaoEncontradaException e) {
			throw new DomainException(e.getMessage(), e);
		}
	}
	
	@DeleteMapping("/{propostaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long propostaId) {
		propostaService.remove(propostaId );	
	}
}
