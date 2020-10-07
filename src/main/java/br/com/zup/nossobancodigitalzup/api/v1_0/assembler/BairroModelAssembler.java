package br.com.zup.nossobancodigitalzup.api.v1_0.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.zup.nossobancodigitalzup.api.v1_0.controller.BairroController;
import br.com.zup.nossobancodigitalzup.api.v1_0.link.BairroLink;
import br.com.zup.nossobancodigitalzup.api.v1_0.link.CidadeLink;
import br.com.zup.nossobancodigitalzup.api.v1_0.model.BairroModel;
import br.com.zup.nossobancodigitalzup.domain.model.Bairro;

@Component
public class BairroModelAssembler extends RepresentationModelAssemblerSupport<Bairro, BairroModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private BairroLink bairroLink;

	@Autowired
	private CidadeLink cidadeLink;

	public BairroModelAssembler() {
		super(BairroController.class, BairroModel.class);
	}

	@Override
	public BairroModel toModel(Bairro bairro) {
		BairroModel bairroModel = createModelWithId(bairro.getBairroId(), bairro);
		modelMapper.map(bairro, bairroModel);
		bairroModel.add(bairroLink.linkToBairros("bairros"));
		bairroModel.getCidade().add(cidadeLink.linkToCidade(bairroModel.getCidade().getCidadeId()));

		return bairroModel;
	}

	@Override
	public CollectionModel<BairroModel> toCollectionModel(Iterable<? extends Bairro> entities) {
		CollectionModel<BairroModel> collectionModel = super.toCollectionModel(entities);
		collectionModel.add(bairroLink.linkToBairros());

		return collectionModel;
	}
}
