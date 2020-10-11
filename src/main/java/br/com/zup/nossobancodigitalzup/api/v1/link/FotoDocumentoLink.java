package br.com.zup.nossobancodigitalzup.api.v1.link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import br.com.zup.nossobancodigitalzup.api.v1.controller.FotoDocumentoController;

@Component
public class FotoDocumentoLink implements ApiLink {
	
	public Link linkToFotoDocumento(String id, String rel) {
		return linkTo(methodOn(FotoDocumentoController.class)
				.findById(id)).withRel(rel);
	}
	
	public Link linkToFotoDocumento(String fotoDocumentoId) {
		return linkToFotoDocumento(fotoDocumentoId, IanaLinkRelations.SELF.value());
	}
}
