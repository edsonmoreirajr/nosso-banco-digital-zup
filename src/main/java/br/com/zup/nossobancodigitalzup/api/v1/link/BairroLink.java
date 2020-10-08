package br.com.zup.nossobancodigitalzup.api.v1.link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;

import br.com.zup.nossobancodigitalzup.api.v1.controller.BairroController;

public class BairroLink extends ApiLink{

	public Link linkToBairro(Long bairroId, String rel) {
		return linkTo(methodOn(BairroController.class)
				.findById(bairroId)).withRel(rel);
	}
	
	public Link linkToBairro(Long bairroId) {
		return linkToBairro(bairroId, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToBairros(String rel) {
		return linkTo(BairroController.class).withRel(rel);
	}
	
	public Link linkToBairros() {
		return linkToBairros(IanaLinkRelations.SELF.value());
	}
}
