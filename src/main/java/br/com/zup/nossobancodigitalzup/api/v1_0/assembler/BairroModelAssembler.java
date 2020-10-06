package br.com.zup.nossobancodigitalzup.api.v1_0.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.zup.nossobancodigitalzup.api.v1_0.ApiLinks;
import br.com.zup.nossobancodigitalzup.api.v1_0.controller.BairroController;
import br.com.zup.nossobancodigitalzup.api.v1_0.model.BairroModel;
import br.com.zup.nossobancodigitalzup.domain.model.Bairro;

@Component
public class BairroModelAssembler extends RepresentationModelAssemblerSupport<Bairro, BairroModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ApiLinks apiLinks;
	
	
	public BairroModelAssembler() {
		super(BairroController.class, BairroModel.class);
	}

	@Override
	public BairroModel toModel(Bairro bairro) {
		return null;
	}
}
