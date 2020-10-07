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
import br.com.zup.nossobancodigitalzup.api.v1_0.assembler.BairroInputDisassembler;
import br.com.zup.nossobancodigitalzup.api.v1_0.assembler.BairroModelAssembler;
import br.com.zup.nossobancodigitalzup.api.v1_0.model.BairroModel;
import br.com.zup.nossobancodigitalzup.api.v1_0.model.input.BairroInput;
import br.com.zup.nossobancodigitalzup.domain.exception.DomainException;
import br.com.zup.nossobancodigitalzup.domain.exception.not_found.BairroNaoEncontradoException;
import br.com.zup.nossobancodigitalzup.domain.model.Bairro;
import br.com.zup.nossobancodigitalzup.domain.service.BairroService;

@RestController
@RequestMapping(path = "/v1/bairros", produces = MediaType.APPLICATION_JSON_VALUE)
public class BairroController {

	@Autowired
	private BairroService bairroService;
	
	@Autowired
	private BairroModelAssembler bairroModelAssembler;
	
	@Autowired
	private BairroInputDisassembler bairroInputDisassembler;
	
	@Autowired
	private PagedResourcesAssembler<Bairro> pagedResourcesAssembler;
	
	@GetMapping
	public PagedModel<BairroModel> listAll(@PageableDefault(size = 10) Pageable pageable) {
		Page<Bairro> bairrosPage = bairroService.listAll(pageable);
		PagedModel<BairroModel> bairrosPagedModel = pagedResourcesAssembler
				.toModel(bairrosPage, bairroModelAssembler);
		
		return bairrosPagedModel;
	}
	
	@GetMapping("/{bairroId}")
	public BairroModel findById(@PathVariable Long bairroId) {
		Bairro bairro = bairroService.findById(bairroId);
		return bairroModelAssembler.toModel(bairro);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public BairroModel add(@RequestBody @Valid BairroInput bairroInput) {
		try {
			Bairro bairro = bairroInputDisassembler.toDomainObject(bairroInput);
			bairro = bairroService.save(bairro);
			BairroModel bairroModel = bairroModelAssembler.toModel(bairro);
			ResourceUriHelper.addUriInResponseHeader(bairroModel.getBairroId());
			
			return bairroModel;
		} catch (BairroNaoEncontradoException e) {
			throw new DomainException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{bairroId}")
	public BairroModel update(@PathVariable Long bairroId,
			@RequestBody @Valid BairroInput bairroInput) {
		try {
			Bairro bairroAtual = bairroService.findById(bairroId);
			bairroInputDisassembler.copyToDomainObject(bairroInput, bairroAtual);
			bairroAtual = bairroService.save(bairroAtual);
			
			return bairroModelAssembler.toModel(bairroAtual);
		} catch (BairroNaoEncontradoException e) {
			throw new DomainException(e.getMessage(), e);
		}
	}
	
	@DeleteMapping("/{bairroId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long bairroId) {
		bairroService.remove(bairroId );	
	}
}
