package br.com.zup.nossobancodigitalzup.api.v1_0.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.zup.nossobancodigitalzup.api.v1_0.controller.EstadoController;
import br.com.zup.nossobancodigitalzup.api.v1_0.link.EstadoLink;
import br.com.zup.nossobancodigitalzup.api.v1_0.model.EstadoModel;
import br.com.zup.nossobancodigitalzup.domain.model.Estado;

@Component
public class EstadoModelAssembler extends RepresentationModelAssemblerSupport<Estado, EstadoModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private EstadoLink estadoLink;
	
	public EstadoModelAssembler() {
		super(EstadoController.class, EstadoModel.class);
	}
	
	@Override
	public EstadoModel toModel(Estado estado) {
		EstadoModel estadoModel = createModelWithId(estado.getEstadoId(), estado);
		modelMapper.map(estado, estadoModel);
     	estadoModel.add(estadoLink.linkToEstados("estados"));
		
		return estadoModel;
	}
	
	@Override
	public CollectionModel<EstadoModel> toCollectionModel(Iterable<? extends Estado> entities) {
		CollectionModel<EstadoModel> collectionModel = super.toCollectionModel(entities);
		collectionModel.add(estadoLink.linkToEstados());
		
		return collectionModel;
	}
}
