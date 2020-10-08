package br.com.zup.nossobancodigitalzup.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.zup.nossobancodigitalzup.api.v1.controller.TransferenciaController;
import br.com.zup.nossobancodigitalzup.api.v1.link.ContaLink;
import br.com.zup.nossobancodigitalzup.api.v1.link.TransferenciaLink;
import br.com.zup.nossobancodigitalzup.api.v1.model.TransferenciaModel;
import br.com.zup.nossobancodigitalzup.domain.model.Transferencia;

@Component
public class TransferenciaModelAssembler extends RepresentationModelAssemblerSupport<Transferencia, TransferenciaModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private TransferenciaLink transferenciaLink;

	@Autowired
	private ContaLink contaLink;

	public TransferenciaModelAssembler() {
		super(TransferenciaController.class, TransferenciaModel.class);
	}

	@Override
	public TransferenciaModel toModel(Transferencia transferencia) {
		TransferenciaModel transferenciaModel = createModelWithId(transferencia.getTransferenciaId(), transferencia);
		modelMapper.map(transferencia, transferenciaModel);
		transferenciaModel.add(transferenciaLink.linkToTransferencias("transferencias"));
		transferenciaModel.getContaBean().add(contaLink.linkToConta(transferenciaModel.getContaBean().getContaId()));

		return transferenciaModel;
	}

	@Override
	public CollectionModel<TransferenciaModel> toCollectionModel(Iterable<? extends Transferencia> entities) {
		CollectionModel<TransferenciaModel> collectionModel = super.toCollectionModel(entities);
		collectionModel.add(transferenciaLink.linkToTransferencias());

		return collectionModel;
	}
}
