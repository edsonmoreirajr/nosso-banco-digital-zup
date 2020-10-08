package br.com.zup.nossobancodigitalzup.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.zup.nossobancodigitalzup.api.v1.controller.ContaController;
import br.com.zup.nossobancodigitalzup.api.v1.link.ContaLink;
import br.com.zup.nossobancodigitalzup.api.v1.link.PropostaLink;
import br.com.zup.nossobancodigitalzup.api.v1.model.ContaModel;
import br.com.zup.nossobancodigitalzup.domain.model.Conta;

@Component
public class ContaModelAssembler extends RepresentationModelAssemblerSupport<Conta, ContaModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ContaLink contaLink;

	@Autowired
	private PropostaLink propostaLink;

	public ContaModelAssembler() {
		super(ContaController.class, ContaModel.class);
	}

	@Override
	public ContaModel toModel(Conta conta) {
		ContaModel contaModel = createModelWithId(conta.getContaId(), conta);
		modelMapper.map(conta, contaModel);
		contaModel.add(contaLink.linkToContas("contas"));
		contaModel.getProposta().add(propostaLink.linkToProposta(contaModel.getProposta().getPropostaId()));

		return contaModel;
	}

	@Override
	public CollectionModel<ContaModel> toCollectionModel(Iterable<? extends Conta> entities) {
		CollectionModel<ContaModel> collectionModel = super.toCollectionModel(entities);
		collectionModel.add(contaLink.linkToContas());

		return collectionModel;
	}
}
