package br.com.zup.nossobancodigitalzup.api.v1_0.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
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
	
	@GetMapping
	public CollectionModel<BairroModel> findAll() {
		return null;
	}
	
	@GetMapping("/{bairroId}")
	public BairroModel findById(@PathVariable Long idBairro) {
		return null;
	}
	
	/*@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public BairroModel add(@RequestBody @Valid BairroInput bairroInput) {
		try {
			Bairro bairro = bairroInputDisassembler.toDomainObject(bairroInput);
			
			bairro = bairroService.salvar(bairro);
			
			BairroModel bairroModel = bairroModelAssembler.toModel(bairro);
			
			ResourceUriHelper.addUriInResponseHeader(bairroModel.getId());
			
			return bairroModel;
		} catch (CidadeNaoEncontradoException e) {
			throw new DomainException(e.getMessage(), e);
		}
	}*/
	
	/*@PutMapping("/{bairroId}")
	public BairroModel update(@PathVariable Long idBairro,
			@RequestBody @Valid BairroInput bairroInput) {
		try {
			Bairro bairroAtual = bairroService.buscarOuFalhar(idBairro);
			
			bairroInputDisassembler.copyToDomainObject(bairroInput, bairroAtual);
			
			bairroAtual = bairroService.salvar(bairroAtual);
			
			return bairroModelAssembler.toModel(bairroAtual);
		} catch (CidadeNaoEncontradoException e) {
			throw new DomainException(e.getMessage(), e);
		}
	}
	
	@DeleteMapping("/{bairroId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long idBairro) {
		bairroService.excluir(idBairro);	
	}*/
}
