package br.com.zup.nossobancodigitalzup.api.v1_0.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.zup.nossobancodigitalzup.api.v1_0.controller.CidadeController;
import br.com.zup.nossobancodigitalzup.api.v1_0.link.CidadeLink;
import br.com.zup.nossobancodigitalzup.api.v1_0.link.EstadoLink;
import br.com.zup.nossobancodigitalzup.api.v1_0.model.CidadeModel;
import br.com.zup.nossobancodigitalzup.domain.model.Cidade;

@Component
public class CidadeModelAssembler extends RepresentationModelAssemblerSupport<Cidade, CidadeModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CidadeLink cidadeLink;

	@Autowired
	private EstadoLink estadoLink;

	public CidadeModelAssembler() {
		super(CidadeController.class, CidadeModel.class);
	}

	@Override
	public CidadeModel toModel(Cidade cidade) {
		CidadeModel cidadeModel = createModelWithId(cidade.getCidadeId(), cidade);
		modelMapper.map(cidade, cidadeModel);
		cidadeModel.add(cidadeLink.linkToCidades("cidades"));
		cidadeModel.getEstado().add(estadoLink.linkToEstado(cidadeModel.getEstado().getEstadoId()));

		return cidadeModel;
	}

	@Override
	public CollectionModel<CidadeModel> toCollectionModel(Iterable<? extends Cidade> entities) {
		CollectionModel<CidadeModel> collectionModel = super.toCollectionModel(entities);
		collectionModel.add(cidadeLink.linkToCidades());

		return collectionModel;
	}
}
