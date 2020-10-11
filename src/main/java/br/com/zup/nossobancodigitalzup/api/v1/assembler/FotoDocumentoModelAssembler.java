package br.com.zup.nossobancodigitalzup.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.zup.nossobancodigitalzup.api.v1.controller.FotoDocumentoController;
import br.com.zup.nossobancodigitalzup.api.v1.link.FotoDocumentoLink;
import br.com.zup.nossobancodigitalzup.api.v1.model.FotoDocumentoModel;
import br.com.zup.nossobancodigitalzup.domain.model.FotoDocumento;

@Component
public class FotoDocumentoModelAssembler 
		extends RepresentationModelAssemblerSupport<FotoDocumento, FotoDocumentoModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private FotoDocumentoLink fotoDocumentoLink;
	
	public FotoDocumentoModelAssembler() {
		super(FotoDocumentoController.class, FotoDocumentoModel.class);
	}
	
	@Override
	public FotoDocumentoModel toModel(FotoDocumento fotoDocumento) {
		FotoDocumentoModel fotoDocumentoModel = modelMapper.map(fotoDocumento, FotoDocumentoModel.class);
		
			fotoDocumentoModel.add(fotoDocumentoLink.linkToFotoDocumento(
					fotoDocumento.getId()));
			
			fotoDocumentoModel.add(fotoDocumentoLink.linkToFotoDocumento(
					fotoDocumento.getId(), "cliente"));
		
		return fotoDocumentoModel;
	}
	
}
