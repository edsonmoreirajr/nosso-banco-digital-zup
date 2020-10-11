package br.com.zup.nossobancodigitalzup.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.zup.nossobancodigitalzup.api.v1.controller.EnderecoController;
import br.com.zup.nossobancodigitalzup.api.v1.link.BairroLink;
import br.com.zup.nossobancodigitalzup.api.v1.link.ClienteLink;
import br.com.zup.nossobancodigitalzup.api.v1.link.EnderecoLink;
import br.com.zup.nossobancodigitalzup.api.v1.model.EnderecoModel;
import br.com.zup.nossobancodigitalzup.domain.model.Endereco;

@Component
public class EnderecoModelAssembler extends RepresentationModelAssemblerSupport<Endereco, EnderecoModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private EnderecoLink enderecoLink;

	@Autowired
	private ClienteLink clienteLink;
	
	@Autowired
	private BairroLink bairroLink;

	public EnderecoModelAssembler() {
		super(EnderecoController.class, EnderecoModel.class);
	}

	@Override
	public EnderecoModel toModel(Endereco endereco) {
		EnderecoModel enderecoModel = createModelWithId(endereco.getEnderecoId(), endereco);
		modelMapper.map(endereco, enderecoModel);
		enderecoModel.add(enderecoLink.linkToEnderecos("enderecos"));
		enderecoModel.getCliente().add(clienteLink.linkToCliente(enderecoModel.getCliente().getCpfCnpj()));
		enderecoModel.getBairro().add(bairroLink.linkToBairro(enderecoModel.getBairro().getBairroId()));

		return enderecoModel;
	}

	@Override
	public CollectionModel<EnderecoModel> toCollectionModel(Iterable<? extends Endereco> entities) {
		CollectionModel<EnderecoModel> collectionModel = super.toCollectionModel(entities);
		collectionModel.add(enderecoLink.linkToEnderecos());

		return collectionModel;
	}
}
