package br.com.zup.nossobancodigitalzup.api.v1.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.nossobancodigitalzup.api.v1.assembler.ContaModelAssembler;
import br.com.zup.nossobancodigitalzup.api.v1.model.ContaModel;
import br.com.zup.nossobancodigitalzup.domain.model.Conta;
import br.com.zup.nossobancodigitalzup.domain.service.ContaService;

@RestController
@RequestMapping(path = "/v1/contas", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContaController {
	@Autowired
	private ContaService contaService;
	
	@Autowired
	private ContaModelAssembler contaModelAssembler;
	
	@Autowired
	private PagedResourcesAssembler<Conta> pagedResourcesAssembler;
	
	@GetMapping
	public PagedModel<ContaModel> listAll(@PageableDefault(size = 10) Pageable pageable) {
		Page<Conta> contasPage = contaService.listAll(pageable);
		PagedModel<ContaModel> contasPagedModel = pagedResourcesAssembler
				.toModel(contasPage, contaModelAssembler);
		
		return contasPagedModel;
	}
	
	@GetMapping("/{contaId}")
	public ContaModel findById(@PathVariable Long contaId) {
		Conta conta = contaService.findById(contaId);
		return contaModelAssembler.toModel(conta);
	}
	
	
	@DeleteMapping("/{contaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long contaId) {
		contaService.remove(contaId );	
	}
}
