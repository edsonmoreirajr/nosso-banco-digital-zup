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
import br.com.zup.nossobancodigitalzup.api.v1.assembler.EnderecoInputDisassembler;
import br.com.zup.nossobancodigitalzup.api.v1.assembler.EnderecoModelAssembler;
import br.com.zup.nossobancodigitalzup.api.v1.model.EnderecoModel;
import br.com.zup.nossobancodigitalzup.api.v1.model.input.EnderecoInput;
import br.com.zup.nossobancodigitalzup.domain.exception.DomainException;
import br.com.zup.nossobancodigitalzup.domain.exception.not_found.EnderecoNaoEncontradoException;
import br.com.zup.nossobancodigitalzup.domain.model.Endereco;
import br.com.zup.nossobancodigitalzup.domain.service.EnderecoService;

@RestController
@RequestMapping(path = "/v1/enderecos", produces = MediaType.APPLICATION_JSON_VALUE)
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;
	
	@Autowired
	private EnderecoModelAssembler enderecoModelAssembler;
	
	@Autowired
	private EnderecoInputDisassembler enderecoInputDisassembler;
	
	@Autowired
	private PagedResourcesAssembler<Endereco> pagedResourcesAssembler;
	
	@GetMapping
	public PagedModel<EnderecoModel> listAll(@PageableDefault(size = 10) Pageable pageable) {
		Page<Endereco> enderecosPage = enderecoService.listAll(pageable);
		PagedModel<EnderecoModel> enderecosPagedModel = pagedResourcesAssembler
				.toModel(enderecosPage, enderecoModelAssembler);
		
		return enderecosPagedModel;
	}
	
	@GetMapping("/{enderecoId}")
	public EnderecoModel findById(@PathVariable Long enderecoId) {
		Endereco endereco = enderecoService.findById(enderecoId);
		return enderecoModelAssembler.toModel(endereco);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EnderecoModel add(@RequestBody @Valid EnderecoInput enderecoInput) {
		try {
			Endereco endereco = enderecoInputDisassembler.toDomainObject(enderecoInput);
			endereco = enderecoService.save(endereco);
			EnderecoModel enderecoModel = enderecoModelAssembler.toModel(endereco);
			ResourceUriHelper.addUriInResponseHeader(enderecoModel.getEnderecoId());
			
			return enderecoModel;
		} catch (EnderecoNaoEncontradoException e) {
			throw new DomainException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{enderecoId}")
	public EnderecoModel update(@PathVariable Long enderecoId,
			@RequestBody @Valid EnderecoInput enderecoInput) {
		try {
			Endereco enderecoAtual = enderecoService.findById(enderecoId);
			enderecoInputDisassembler.copyToDomainObject(enderecoInput, enderecoAtual);
			enderecoAtual = enderecoService.save(enderecoAtual);
			
			return enderecoModelAssembler.toModel(enderecoAtual);
		} catch (EnderecoNaoEncontradoException e) {
			throw new DomainException(e.getMessage(), e);
		}
	}
	
	@DeleteMapping("/{enderecoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long enderecoId) {
		enderecoService.remove(enderecoId );	
	}
	
}
