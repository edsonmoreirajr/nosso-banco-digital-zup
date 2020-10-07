package br.com.zup.nossobancodigitalzup.api.v1_0.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.zup.nossobancodigitalzup.api.v1_0.controller.ClienteController;
import br.com.zup.nossobancodigitalzup.api.v1_0.link.ClienteLink;
import br.com.zup.nossobancodigitalzup.api.v1_0.model.ClienteModel;
import br.com.zup.nossobancodigitalzup.domain.model.Cliente;

@Component
public class ClienteModelAssembler extends RepresentationModelAssemblerSupport<Cliente, ClienteModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ClienteLink clienteLink;
	
	public ClienteModelAssembler() {
		super(ClienteController.class, ClienteModel.class);
	}
	
	@Override
	public ClienteModel toModel(Cliente cliente) {
		ClienteModel clienteModel = createModelWithId(cliente.getClienteCpfCnpj(), cliente);
		modelMapper.map(cliente, clienteModel);
     	clienteModel.add(clienteLink.linkToClientes("clientes"));
		
		return clienteModel;
	}
	
	@Override
	public CollectionModel<ClienteModel> toCollectionModel(Iterable<? extends Cliente> entities) {
		CollectionModel<ClienteModel> collectionModel = super.toCollectionModel(entities);
		collectionModel.add(clienteLink.linkToClientes());
		
		return collectionModel;
	}
}
