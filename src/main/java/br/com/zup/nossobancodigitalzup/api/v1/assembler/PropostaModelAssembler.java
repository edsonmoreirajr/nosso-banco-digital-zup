package br.com.zup.nossobancodigitalzup.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.zup.nossobancodigitalzup.api.v1.controller.PropostaController;
import br.com.zup.nossobancodigitalzup.api.v1.link.ClienteLink;
import br.com.zup.nossobancodigitalzup.api.v1.link.PropostaLink;
import br.com.zup.nossobancodigitalzup.api.v1.model.PropostaModel;
import br.com.zup.nossobancodigitalzup.domain.model.Proposta;

@Component
public class PropostaModelAssembler extends RepresentationModelAssemblerSupport<Proposta, PropostaModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PropostaLink propostaLink;

	@Autowired
	private ClienteLink clienteLink;

	public PropostaModelAssembler() {
		super(PropostaController.class, PropostaModel.class);
	}

	@Override
	public PropostaModel toModel(Proposta proposta) {
		PropostaModel propostaModel = createModelWithId(proposta.getPropostaId(), proposta);
		modelMapper.map(proposta, propostaModel);
		propostaModel.add(propostaLink.linkToPropostas("propostas"));
		propostaModel.getCliente().add(clienteLink.linkToCliente(propostaModel.getCliente().getClienteCpfCnpj()));

		return propostaModel;
	}

	@Override
	public CollectionModel<PropostaModel> toCollectionModel(Iterable<? extends Proposta> entities) {
		CollectionModel<PropostaModel> collectionModel = super.toCollectionModel(entities);
		collectionModel.add(propostaLink.linkToPropostas());

		return collectionModel;
	}
}
